package com.template.model.enums;

import lombok.Getter;

/**
 * Перечсление статусов
 */
@Getter
public enum Status {
    PROCESSING,
    SUCCESS,
    FAILED;

    public static Status from(String status) {
        for (Status statusItem : Status.values()) {
            if (statusItem.toString().equals(status)) {
                return statusItem;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }
}
