package com.lemoncat.tbtasksrservice.DTO.Task;

public record CreateTask(
        Long categoryId,
        String name,
        String deadlineDate,
        String priority
) {
}
