package com.template.model.entity;

import com.template.model.enums.Status;
import com.template.model.enums.converter.StatusConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Шаблон (пример) сущности
 */
@Getter
@Setter
@Entity(name = "template")
public class TemplateEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Integer value;

    @Column(name = "status")
    @Convert(converter = StatusConverter.class)
    private Status status;

    @Column(name = "date")
    private LocalDate date;
}
