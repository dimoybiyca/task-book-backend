package com.lemoncat.tbtasksrservice.DTO.Task;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDTO(
        Long id,
        Long categoryId,
        String name,
        String deadlineDate,
        LocalDateTime createdDate,
        String status,
        String priority) {
}
