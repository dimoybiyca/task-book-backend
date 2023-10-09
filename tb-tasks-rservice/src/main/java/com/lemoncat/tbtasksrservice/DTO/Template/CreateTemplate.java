package com.lemoncat.tbtasksrservice.DTO.Template;

public record CreateTemplate(
        Long categoryId,
        String name,
        String priority) {
}
