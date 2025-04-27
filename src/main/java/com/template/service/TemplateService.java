package com.template.service;

import com.template.mapper.TemplateMapper;
import com.template.model.entity.TemplateEntity;
import com.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.openapi.template.model.TemplateCommon;
import org.openapi.template.model.TemplateResponseList;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Сервис для работы с шаблонным классом
 */
@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateMapper templateMapper;
    private final TemplateRepository templateRepository;

    /**
     * Получить список шаблонов
     * @return список шаблонов
     */
    public TemplateResponseList getTemplates() {
        List<TemplateEntity> templateList = templateRepository.findAll();

        if(CollectionUtils.isEmpty(templateList)) {

        }

        List<TemplateCommon> templateCommonList = templateList.stream().map(templateMapper::toTemplateCommon).toList();
        return new TemplateResponseList().templates(templateCommonList);
    }
}
