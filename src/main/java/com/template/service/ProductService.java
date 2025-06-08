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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${spring.kafka.services.payment.command.topic}")
    private String paymentCommandTopic;
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
            kafkaTemplate.send(paymentCommandTopic, objectMapper.writeValueAsString(productMapper.toProductTookEvent(productEntity)));
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
            kafkaTemplate.send(orderCommandTopic, objectMapper.writeValueAsString(productMapper.toProductCanceledEvent(productEntity)));
        } catch (Exception e) {
            log.error("Ошибка диссериализации");
            throw new RuntimeException();
        }
    }
}
