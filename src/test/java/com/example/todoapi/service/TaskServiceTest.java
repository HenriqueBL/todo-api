package com.example.todoapi.service;

import com.example.todoapi.dto.TaskRequestDTO;
import com.example.todoapi.dto.TaskResponseDTO;
import com.example.todoapi.exception.TaskNotFoundException;
import com.example.todoapi.model.Task;
import com.example.todoapi.repository.TaskRepository;
import com.example.todoapi.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Task task;
    private TaskRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id(1L)
                .title("Estudar Spring Boot")
                .description("Fazer o projeto de To-Do List")
                .completed(false)
                .build();
        requestDTO = new TaskRequestDTO();
        requestDTO.setTitle("Estudar Spring Boot");
        requestDTO.setDescription("Fazer o projeto de To-Do List");
        requestDTO.setCompleted(false);
    }

    @Test
    @DisplayName("Deve retornar todas as tarefas")
    void findAll_deveRetornarLista() {
        when(taskRepository.findAll()).thenReturn(List.of(task));
        List<TaskResponseDTO> result = taskService.findAll();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getTitle()).isEqualTo("Estudar Spring Boot");
    }

    @Test
    @DisplayName("Deve criar uma tarefa com sucesso")
    void create_deveSalvarTarefa() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        TaskResponseDTO result = taskService.create(requestDTO);
        assertThat(result.getTitle()).isEqualTo("Estudar Spring Boot");
        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve lancar excecao ao buscar id inexistente")
    void findById_deveLancarExcecao_quandoNaoExiste() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> taskService.findById(99L))
                .isInstanceOf(TaskNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    @DisplayName("Deve deletar tarefa existente")
    void delete_deveDeletarComSucesso() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);
        assertThatCode(() -> taskService.delete(1L)).doesNotThrowAnyException();
        verify(taskRepository).delete(task);
    }
}
