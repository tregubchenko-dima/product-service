package com.template.controller;

import com.template.service.CircuitBreakerService;
import lombok.RequiredArgsConstructor;
import org.openapi.template.CurcuitBreakerApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для демонстрации реализации паттерна CircuitBreaker
 */
@RestController
@RequiredArgsConstructor
public class CircuitBreakerController implements CurcuitBreakerApi {

    private final CircuitBreakerService circuitBreakerService;

    @Override
    public ResponseEntity<String> getTestCurcuitBreaker(Integer num) {
        return ResponseEntity.ok(circuitBreakerService.call(num));
    }
}
