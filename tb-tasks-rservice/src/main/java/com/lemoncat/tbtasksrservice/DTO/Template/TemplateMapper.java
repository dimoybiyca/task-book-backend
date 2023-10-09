package com.lemoncat.tbtasksrservice.DTO.Template;

import com.lemoncat.tbtasksrservice.models.Category;
import com.lemoncat.tbtasksrservice.models.Template;
import com.lemoncat.tbtasksrservice.services.CategoryService;
import com.lemoncat.tbtasksrservice.services.PriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateMapper {

    private final CategoryService categoryService;
    private final PriorityService priorityService;

    public TemplateDTO templateToDTO(Template template) {
        return TemplateDTO.builder()
                .id(template.getId())
                .categoryId(template.getCategory().getId())
                .name(template.getTemplateTitle())
                .priority(template.getPriority().getName())
                .build();
    }

    public Template toObject(TemplateDTO dto, String ownerId) {
        return Template.builder()
                .id(dto.id())
                .ownerId(ownerId)
                .templateTitle(dto.name())
                .category(
                        Category.builder()
                                .id(dto.categoryId())
                                .build())
                .priority(priorityService.getByName(dto.priority()))
                .build();
    }

    public Template toObject(CreateTemplate request, String ownerId) {
        return Template.builder()
                .ownerId(ownerId)
                .templateTitle(request.name())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build())
                .priority(priorityService.getByName(request.priority()))
                .build();
    }
}
