package com.lemoncat.tbtasksrservice.DTO.Template;

import lombok.Builder;

@Builder
public record TemplateDTO(
        Long id,
        Long categoryId,
        String name,
        String priority) {
}
