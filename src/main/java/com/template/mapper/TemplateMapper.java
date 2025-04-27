package com.template.mapper;

import com.template.model.entity.TemplateEntity;
import org.mapstruct.Mapper;
import org.openapi.template.model.TemplateCommon;

@Mapper(componentModel = "spring")
public interface TemplateMapper {

    TemplateCommon toTemplateCommon(TemplateEntity entity);
}
