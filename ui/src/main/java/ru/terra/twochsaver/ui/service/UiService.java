package ru.terra.twochsaver.ui.service;

import org.springframework.stereotype.Service;
import ru.terra.twochsaver.shared.ThreadState;
import ru.terra.twochsaver.shared.entity.TwochThread;
import ru.terra.twochsaver.ui.db.repos.TwochThreadRepo;
import ru.terra.twochsaver.ui.exception.AlreadyAddedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UiService {
    private final TwochThreadRepo twochThreadRepo;

    public UiService(TwochThreadRepo twochThreadRepo) {
        this.twochThreadRepo = twochThreadRepo;
    }

    public Integer startDownload(final String url) throws AlreadyAddedException {
        final TwochThread twochThread = new TwochThread();
        parseUrl(url, twochThread);
        if (twochThreadRepo.existsById(twochThread.getId())) {
            throw new AlreadyAddedException();
        } else {
            twochThreadRepo.save(twochThread);
        }
        return twochThread.getId();
    }

    private void parseUrl(final String url, final TwochThread twochThread) {
        final Integer thread = Integer.parseInt(url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf(".")));
        final String resUrl = url.substring(0, url.indexOf("/res"));
        final String board = resUrl.substring(resUrl.lastIndexOf("/") + 1);
        twochThread.setBoard(board);
        twochThread.setId(thread);
        twochThread.setThreadState(ThreadState.NOT_STARTED);
        twochThread.setUpdates(0);
        twochThread.setAdded(new Date());
        twochThread.setUpdated(new Date());
        twochThread.setLastCount(0);
    }

    public List<TwochThread> getThreads() {
        final List<TwochThread> result = new ArrayList<>();
        twochThreadRepo.findAll().forEach(result::add);
        return result;
    }
}
