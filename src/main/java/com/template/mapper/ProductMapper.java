package com.template.mapper;

import com.template.model.entity.ProductEntity;
import com.template.model.event.ProductCanceledEvent;
import com.template.model.event.ProductTookEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "count", constant = "1")
    ProductTookEvent toProductTookEvent(UUID orderId, ProductEntity entity);

    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "count", source = "entity.count")
    ProductCanceledEvent toProductCanceledEvent(UUID orderId, ProductEntity entity);
}