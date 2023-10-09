package com.lemoncat.tbtasksrservice.services;

import com.lemoncat.tbtasksrservice.DTO.Task.CreateTask;
import com.lemoncat.tbtasksrservice.DTO.Task.TaskDTO;
import com.lemoncat.tbtasksrservice.DTO.Task.TaskMapper;
import com.lemoncat.tbtasksrservice.models.Category;
import com.lemoncat.tbtasksrservice.models.Task;
import com.lemoncat.tbtasksrservice.models.enums.StatusEnum;
import com.lemoncat.tbtasksrservice.repositories.CategoryRepository;
import com.lemoncat.tbtasksrservice.repositories.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final StatusService statusService;
    private final TaskMapper taskMapper;

    public List<TaskDTO> getAllDTO(String ownerId) {
        return this.getAll(ownerId).stream()
                .map(taskMapper::taskToDTO)
                .toList();
    }

    public List<TaskDTO> getAllByCategory(Long categoryId , String ownerId) {
        Optional<Category> category = categoryRepository.getByIdAndOwnerId(categoryId, ownerId);

        if(category.isPresent()) {
             return category.get().getTasks().stream()
                    .map(taskMapper::taskToDTO)
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException(
                    String.format("Category with id: %s not found", categoryId)
            );
        }
    }
    public TaskDTO getTaskDTOById(Long id, String ownerId) {
        return taskMapper.taskToDTO(this.getTaskById(id, ownerId));
    }

    @Transactional
    public TaskDTO create(CreateTask createTask, String ownerId) {
        Task task = taskMapper.toObject(createTask, ownerId);

        return taskMapper.taskToDTO(taskRepository.save(task));
    }

    @Transactional
    public TaskDTO update(TaskDTO taskDTO, String ownerId) {
        Task oldTask = this.getTaskById(taskDTO.id(), ownerId);

        Task task  = taskMapper.toObject(taskDTO, ownerId);
        task.setId(oldTask.getId());

        return taskMapper.taskToDTO(taskRepository.save(task));
    }

    @Transactional
    public void delete(Long id, String ownerId) {
        Task task = this.getTaskById(id, ownerId);
        task.setStatus(statusService.getByName(StatusEnum.DELETED.name()));

        taskRepository.save(task);
    }

    private List<Task> getAll(String ownerId) {
        List<Category> categories = categoryRepository.getAllByOwnerId(ownerId);

        return categories.stream()
                .map(Category::getTasks)
                .flatMap(Collection::stream)
                .filter(task -> !Objects.equals(task.getStatus().getName(), StatusEnum.DELETED.name()))
                .collect(Collectors.toList());
    }

    private Task getTaskById(Long id, String ownerId) {
        return this.getAll(ownerId).stream()
                .filter(task -> Objects.equals(task.getId(), id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Task with id: %s doesn't exist", id)
                ));
    }
}
