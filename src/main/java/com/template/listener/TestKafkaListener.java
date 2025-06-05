package com.template.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestKafkaListener {

    @KafkaListener(topics = "template-in", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        log.info("MESSAGE RECEIVED: %s".formatted(message));
    }
}
