package com.template.controller;

import lombok.RequiredArgsConstructor;
import org.openapi.template.KafkaApi;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaSendController implements KafkaApi {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public ResponseEntity<String> sendKafka() {
        kafkaTemplate.send("template-in", "Some message");
        return ResponseEntity.ok("SUCCESS");
    }
}
