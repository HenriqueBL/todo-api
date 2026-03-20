package com.example.todoapi.service.impl;

import com.example.todoapi.dto.TaskRequestDTO;
import com.example.todoapi.dto.TaskResponseDTO;
import com.example.todoapi.exception.TaskNotFoundException;
import com.example.todoapi.model.Task;
import com.example.todoapi.repository.TaskRepository;
import com.example.todoapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> findAll() {
        log.info("Buscando todas as tarefas");
        return taskRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskResponseDTO> findByCompleted(boolean completed) {
        log.info("Buscando tarefas com completed={}", completed);
        return taskRepository.findByCompleted(completed)
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskResponseDTO findById(Long id) {
        log.info("Buscando tarefa com id={}", id);
        Task task = findTaskOrThrow(id);
        return toResponseDTO(task);
    }

    @Override
    @Transactional
    public TaskResponseDTO create(TaskRequestDTO dto) {
        log.info("Criando nova tarefa: {}", dto.getTitle());
        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .completed(dto.isCompleted())
                .build();
        Task saved = taskRepository.save(task);
        return toResponseDTO(saved);
    }

    @Override
    @Transactional
    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        log.info("Atualizando tarefa com id={}", id);
        Task task = findTaskOrThrow(id);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(dto.isCompleted());
        Task updated = taskRepository.save(task);
        return toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deletando tarefa com id={}", id);
        Task task = findTaskOrThrow(id);
        taskRepository.delete(task);
    }

    private Task findTaskOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    private TaskResponseDTO toResponseDTO(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.isCompleted())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
