package com.lemoncat.tbtasksrservice.controllers;

import com.lemoncat.tbtasksrservice.DTO.Task.CreateTask;
import com.lemoncat.tbtasksrservice.DTO.Task.TaskDTO;
import com.lemoncat.tbtasksrservice.services.TaskService;
import io.micrometer.tracing.annotation.SpanTag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TasksController {

    private final TaskService taskService;

    @GetMapping("tasks")
    public Map<String,List<TaskDTO>> getTasks(
            @AuthenticationPrincipal Jwt principal) {
        List<TaskDTO> tasks = taskService.getAllDTO(principal.getSubject());

        return Map.of("tasks", tasks);
    }

    @GetMapping("categories/{id}/tasks")
    public Map<String,List<TaskDTO>> getTasksByCategory(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt principal) {
        List<TaskDTO> tasks = taskService.getAllByCategory(id, principal.getSubject());

        return Map.of("tasks", tasks);
    }

    @GetMapping("tasks/{id}")
    public TaskDTO getTask(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt principal) {
        return taskService.getTaskDTOById(id, principal.getSubject());
    }

    @PostMapping("tasks")
    public Map<String, TaskDTO> createTask(
            @RequestBody CreateTask createTask,
            @AuthenticationPrincipal Jwt principal) {
        TaskDTO taskResponse = taskService.create(createTask, principal.getSubject()) ;

        return Map.of("task", taskResponse);
    }

    @PutMapping("tasks")
    public Map<String, TaskDTO> updateTask(
            @RequestBody TaskDTO taskDTO,
            @AuthenticationPrincipal Jwt principal) {
        TaskDTO taskResponse = taskService.update(taskDTO, principal.getSubject());

        return Map.of("task", taskResponse);
    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt principal) {
        taskService.delete(id, principal.getSubject());

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
