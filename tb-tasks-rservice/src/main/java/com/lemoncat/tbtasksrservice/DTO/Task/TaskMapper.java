package com.lemoncat.tbtasksrservice.DTO.Task;

import com.lemoncat.tbtasksrservice.models.Task;
import com.lemoncat.tbtasksrservice.models.enums.StatusEnum;
import com.lemoncat.tbtasksrservice.services.CategoryService;
import com.lemoncat.tbtasksrservice.services.PriorityService;
import com.lemoncat.tbtasksrservice.services.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private final CategoryService categoryService;
    private final StatusService statusService;
    private final PriorityService priorityService;

    public TaskDTO taskToDTO(Task task) {

        return TaskDTO.builder()
                .id(task.getId())
                .categoryId(task.getCategory().getId())
                .name(task.getTaskTitle())
                .createdDate(task.getCreatedDate())
                .deadlineDate(task.getDeadlineDate().toString())
                .priority(task.getPriority().getName())
                .status(task.getStatus().getName())
                .build();
    }

    public Task toObject(TaskDTO taskResponse, String ownerId) {
        return Task.builder()
                .id(taskResponse.id())
                .taskTitle(taskResponse.name())
                .createdDate(taskResponse.createdDate())
                .deadlineDate(LocalDate.parse(taskResponse.deadlineDate().substring(0,10), formatter).atStartOfDay())
                .category(categoryService.getById(taskResponse.categoryId(), ownerId))
                .status(statusService.getByName(taskResponse.status()))
                .priority(priorityService.getByName(taskResponse.priority()))
                .build();
    }

    public Task toObject(CreateTask createTask, String ownerId) {

        return Task.builder()
                .taskTitle(createTask.name())
                .createdDate(LocalDateTime.now())
                .deadlineDate(LocalDate.parse(createTask.deadlineDate(), formatter).atStartOfDay())
                .category(categoryService.getById(createTask.categoryId(), ownerId))
                .status(statusService.getByName(StatusEnum.IN_PROGRESS.name()))
                .priority(priorityService.getByName(createTask.priority()))
                .build();
    }
}
