package com.template.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.mapper.ProductMapper;
import com.template.model.entity.ProductEntity;
import com.template.model.event.OrderBookedEvent;
import com.template.model.event.PaymentCanceledEvent;
import com.template.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private static final String COMMAND_HEADER_NAME = "command";

    @Value("${spring.kafka.services.order.command.topic}")
    private String orderCommandTopic;

    private final ObjectMapper objectMapper;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void takeProductToOrder(OrderBookedEvent event) {
        ProductEntity productEntity = productRepository.getByName(event.getName());
        productEntity.setCount(productEntity.getCount() - 1);
        productRepository.save(productEntity);

        try {
            kafkaTemplate.send(
                    MessageBuilder.withPayload(objectMapper.writeValueAsString(productMapper.toProductTookEvent(productEntity)))
                            .setHeader(KafkaHeaders.TOPIC, orderCommandTopic)
                            .setHeader(COMMAND_HEADER_NAME, "ProductTookEvent")
                            .build()
            );
        } catch (Exception e) {
            log.error("Ошибка диссериализации");
            throw new RuntimeException();
        }
    }

    @Transactional
    public void cancelProductFromOrder(PaymentCanceledEvent event) {
        ProductEntity productEntity = productRepository.getByName(event.getName());
        productEntity.setCount(productEntity.getCount() + 1);
        productRepository.save(productEntity);

        try {
            kafkaTemplate.send(
                    MessageBuilder.withPayload(objectMapper.writeValueAsString(productMapper.toProductCanceledEvent(productEntity)))
                            .setHeader(KafkaHeaders.TOPIC, orderCommandTopic)
                            .setHeader(COMMAND_HEADER_NAME, "ProductCanceledEvent")
                            .build()
            );
        } catch (Exception e) {
            log.error("Ошибка диссериализации");
            throw new RuntimeException();
        }
    }
}
