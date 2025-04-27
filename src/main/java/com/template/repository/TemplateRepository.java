package com.template.repository;


import com.template.model.entity.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий для работы с {@link TemplateEntity}
 */
public interface TemplateRepository extends JpaRepository<TemplateEntity, UUID> {
}
