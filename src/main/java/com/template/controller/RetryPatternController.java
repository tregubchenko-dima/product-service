package com.template.controller;

import com.template.service.RetryPatternService;
import lombok.RequiredArgsConstructor;
import org.openapi.template.RetryPatternApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для демонстрации реализации паттерна Retry
 */
@RestController
@RequiredArgsConstructor
public class RetryPatternController implements RetryPatternApi {

    private final RetryPatternService retryPatternService;

    @Override
    public ResponseEntity<String> getTestRetryPattern(Integer num) {
        return ResponseEntity.ok(retryPatternService.callMethod(num));
    }
}
