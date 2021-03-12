package ru.terra.twochsaver.scheduler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.terra.twochsaver.scheduler.db.repos.TwochFileRepo;
import ru.terra.twochsaver.scheduler.db.repos.TwochThreadRepo;
import ru.terra.twochsaver.scheduler.exception.UrlNotFoundException;
import ru.terra.twochsaver.scheduler.queue.DownloadSender;
import ru.terra.twochsaver.scheduler.util.NetworkUtil;
import ru.terra.twochsaver.shared.DownloadEntity;
import ru.terra.twochsaver.shared.DownloadState;
import ru.terra.twochsaver.shared.ThreadState;
import ru.terra.twochsaver.shared.dto.TwochFileDto;
import ru.terra.twochsaver.shared.entity.TwochFile;
import ru.terra.twochsaver.shared.entity.TwochThread;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static ru.terra.twochsaver.shared.QueueConstants.DOWNLOAD_TOPIC;

@Component
@Log
public class TwochSchedulerService {
    private final static Long MAX_TIME = (long) (10 * 60 * 1000);

    private final DownloadSender downloadSender;
    private final TwochFileRepo twochFileRepo;
    private final TwochThreadRepo twochThreadRepo;

    public TwochSchedulerService(DownloadSender downloadSender, TwochFileRepo twochFileRepo, TwochThreadRepo twochThreadRepo) {
        this.downloadSender = downloadSender;
        this.twochFileRepo = twochFileRepo;
        this.twochThreadRepo = twochThreadRepo;
    }

    @Scheduled(fixedRateString = "10000")
    public void doScheduledTask() throws ExecutionException, InterruptedException {
        //1 list all not started
        twochThreadRepo.findAllByThreadState(ThreadState.NOT_STARTED).forEach(this::processThread);
        //2 every not started -> change to scheduled and load json, if error status to error
        //3 parse json and get files, check db for these files, send files to download service if status is NOT_STARTED
        //4 change status to SCHEDULED
        //5 list all SCHEDULED
        twochThreadRepo.findAllByThreadState(ThreadState.SCHEDULED)
                .stream()
                .filter(thr -> {
                    return System.currentTimeMillis() - thr.getUpdated().getTime() > MAX_TIME;
                })
                .forEach(this::processThread);
        //6 if last update > MAX_WAIT change to scheduled and go to 3
    }

    private void processThread(final TwochThread twochThread) {
        try {
            final List<TwochFileDto> twochFileDtos = NetworkUtil.loadFilesForThread(twochThread.getId(), twochThread.getBoard());
            if (twochFileDtos != null) {
                final List<String> dbFileIds = twochFileRepo.findAllByThreadId(twochThread.getId()).parallelStream().map(TwochFile::getId).collect(Collectors.toList());
                final List<TwochFile> twochFiles = twochFileDtos
                        .parallelStream()
                        .map(dto -> parseFileDto(dto, twochThread.getId()))
                        .filter(tf -> !dbFileIds.contains(tf.getId()))
                        .collect(Collectors.toList());

                twochFileRepo.saveAll(twochFiles);
                twochFiles.forEach(tf -> emitDownload(tf, twochThread.getBoard()));
                twochThread.setThreadState(ThreadState.SCHEDULED);
                twochThread.setUpdates(twochThread.getUpdates() + 1);
            } else {
                twochThread.setThreadState(ThreadState.COMPLETE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UrlNotFoundException e) {
            log.info("Thread " + twochThread.getId() + " not found");
            twochThread.setThreadState(ThreadState.COMPLETE);
        } finally {
            twochThreadRepo.save(twochThread);
        }
    }

    private void emitDownload(final TwochFile twochFile, final String board) {
        final DownloadEntity downloadEntity = new DownloadEntity(
                twochFile.getId(),
                board,
                twochFile.getThreadId().toString()
        );
        try {
            downloadSender.sendMessage(DOWNLOAD_TOPIC, "IN_KEY", new ObjectMapper().writeValueAsString(downloadEntity));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private TwochFile parseFileDto(final TwochFileDto dto, final Integer threadId) {
        final TwochFile result = new TwochFile();
        result.setId(NetworkUtil.BASE_URL + dto.getPath());
        result.setDisplayName(dto.getDisplayname());
        result.setFileName(dto.getPath());
        result.setFullName(dto.getFullname());
        result.setHash(dto.getMd5());
        result.setSize(dto.getSize().longValue());
        result.setThreadId(threadId);
        result.setState(DownloadState.NOT_STARTED);
        return result;
    }
}
