package com.template.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * Сервис, демонстрирующий применение паттерна Retry
 */
@Slf4j
@Service
@EnableRetry
public class RetryPatternService {

    @Retryable(retryFor = IllegalArgumentException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
    public String callMethod(Integer num) {
        log.info("-------callMethod-------");
        if (num < 10) {
            throw new IllegalArgumentException();
        }

        if (num < 20) {
            throw new ArithmeticException();
        }

        return "Success";
    }

    @Recover
    public String fallbackMethod(Integer num, Exception e) {
        return "fallback for: " + num;
    }
}
