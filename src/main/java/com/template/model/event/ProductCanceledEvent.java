package com.template.model.event;

import lombok.Data;

@Data
public class ProductCanceledEvent {

    private String name;
    private Integer price;
    private Integer count;
}
