package com.template.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

/**
 * Сервис, демонстрирующий применение паттерна Circuit Breaker
 */
@Service
public class CircuitBreakerService {

    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackMethod")
    public String call(Integer num) {
        if (num < 10) throw new RuntimeException();
        return "Success result";
    }

    public String fallbackMethod(Integer num, Throwable ex) {
        return "Fallback method result for num = %s".formatted(num);
    }
}
