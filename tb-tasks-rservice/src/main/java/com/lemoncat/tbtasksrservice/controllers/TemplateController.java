package com.lemoncat.tbtasksrservice.controllers;

import com.lemoncat.tbtasksrservice.DTO.Template.CreateTemplate;
import com.lemoncat.tbtasksrservice.DTO.Template.TemplateDTO;
import com.lemoncat.tbtasksrservice.services.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService templateService;

    @GetMapping("templates")
    public Map<String,List<TemplateDTO>> getTemplates(
            @AuthenticationPrincipal Jwt principal) {
        List<TemplateDTO> templates = templateService.getAllDTOByOwner(principal.getSubject());

        return Map.of("templates", templates);
    }

    @PostMapping("templates")
    public Map<String, TemplateDTO> createTemplate(
            @RequestBody CreateTemplate request,
            @AuthenticationPrincipal Jwt principal) {
        TemplateDTO response = templateService.create(request, principal.getSubject());

        return Map.of("template", response);
    }
}
