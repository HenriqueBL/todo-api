package com.example.todoapi.service;

import com.example.todoapi.dto.TaskRequestDTO;
import com.example.todoapi.dto.TaskResponseDTO;
import java.util.List;

public interface TaskService {

    List<TaskResponseDTO> findAll();

    List<TaskResponseDTO> findByCompleted(boolean completed);

    TaskResponseDTO findById(Long id);

    TaskResponseDTO create(TaskRequestDTO dto);

    TaskResponseDTO update(Long id, TaskRequestDTO dto);

    void delete(Long id);
}
