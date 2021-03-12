package ru.terra.twochsaver.scheduler.queue;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class DownloadSender {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DownloadSender(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public ListenableFuture<SendResult<String, String>> sendMessage(String topic, String key, String message) {
        return this.kafkaTemplate.send(topic, key, message);
    }
}
