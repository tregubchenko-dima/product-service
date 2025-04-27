package com.template.controller;


import com.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.openapi.template.TemplatesApi;
import org.openapi.template.model.TemplateResponseList;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Шаблонный контроллер
 */
@RestController
@RequiredArgsConstructor
public class TemplateController implements TemplatesApi {

    private final TemplateService templateService;

    @Override
    public ResponseEntity<TemplateResponseList> getTemplate() {
        return ResponseEntity.ok(templateService.getTemplates());
    }
}
