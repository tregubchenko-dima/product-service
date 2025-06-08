package com.template.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.model.event.OrderBookedEvent;
import com.template.model.event.PaymentCanceledEvent;
import com.template.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventKafkaListener {

    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @KafkaListener(topics = "${spring.kafka.topic.command.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void commandListen(ConsumerRecord<String, String> event) {
        log.info("MESSAGE RECEIVED: %s".formatted(event));
        var header = event.headers().lastHeader("command");
        if (header != null) {
            if (new String(header.value()).equals("OrderBookedEvent")) {
                try {
                    productService.takeProductToOrder(objectMapper.readValue(event.value(), OrderBookedEvent.class));
                } catch (Exception e) {
                    log.error("Не удалось десериализовать OrderBookedEvent");
                    throw new RuntimeException(e);
                }
            } else if (new String(header.value()).equals("PaymentCanceledEvent")) {
                try {
                    productService.cancelProductFromOrder(objectMapper.readValue(event.value(), PaymentCanceledEvent.class));
                } catch (Exception e) {
                    log.error("Не удалось десериализовать PaymentCanceledEvent");
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
