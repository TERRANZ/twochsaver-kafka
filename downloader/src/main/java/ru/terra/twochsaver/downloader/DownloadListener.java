package ru.terra.twochsaver.downloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.terra.twochsaver.shared.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static ru.terra.twochsaver.shared.QueueConstants.DOWNLOAD_TOPIC;

@Service
public class DownloadListener {
    private final Logger logger = LoggerFactory.getLogger(DownloadListener.class);

    @KafkaListener(topics = {DOWNLOAD_TOPIC})
    public void consume(final @Payload String message,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                        final Acknowledgment acknowledgment
    ) throws IOException {
        final DownloadEntity downloadEntity = new ObjectMapper().readValue(message, DownloadEntity.class);
        doDownload(downloadEntity.getBoard() + "/" + downloadEntity.getThread(), downloadEntity.getUrl());
        acknowledgment.acknowledge();
    }

    private void doDownload(String folder, String url) throws IOException {
        new File(folder).mkdirs();
        for (int i = 0; i <= 2; i++) {

            final Path path = Paths.get(folder + url.substring(url.lastIndexOf("/")));
            if (!path.toFile().exists())
                Files.copy(new URL(url).openStream(), path, StandardCopyOption.REPLACE_EXISTING);
            break;
        }
    }
}
