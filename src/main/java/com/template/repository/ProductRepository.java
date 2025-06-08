package com.template.repository;


import com.template.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий для работы с {@link ProductEntity}
 */
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

    ProductEntity getByName(String name);
}
