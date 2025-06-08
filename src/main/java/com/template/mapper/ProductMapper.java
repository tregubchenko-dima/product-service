package com.template.mapper;

import com.template.model.entity.ProductEntity;
import com.template.model.event.ProductCanceledEvent;
import com.template.model.event.ProductTookEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "count", constant = "1")
    ProductTookEvent toProductTookEvent(ProductEntity entity);

    @Mapping(target = "name", source = "entity.name")
    @Mapping(target = "price", source = "entity.price")
    @Mapping(target = "count", source = "entity.count")
    ProductCanceledEvent toProductCanceledEvent(ProductEntity entity);
}