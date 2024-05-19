package com.labdessoft.roteiro01.unit.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.labdessoft.roteiro01.entity.Task;
import com.labdessoft.roteiro01.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskRepository taskRepository;

    @Test
    public void listAllTasks_whenTasksExist_shouldReturnTasks() throws Exception {
        // Arrange
        Task task1 = new Task(1L, "Descrição da tarefa 1", false);
        Task task2 = new Task(2L, "Descrição da tarefa 2", true);
        List<Task> tasks = Arrays.asList(task1, task2);

        when(taskRepository.findAll()).thenReturn(tasks);

        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(task1.getId()))
                .andExpect(jsonPath("$[0].description").value(task1.getDescription()))
                .andExpect(jsonPath("$[0].completed").value(task1.isCompleted()))
                .andExpect(jsonPath("$[1].id").value(task2.getId()))
                .andExpect(jsonPath("$[1].description").value(task2.getDescription()))
                .andExpect(jsonPath("$[1].completed").value(task2.isCompleted()));
    }

    @Test
    public void listAllTasks_whenNoTasksExist_shouldReturnNoContent() throws Exception {

        when(taskRepository.findAll()).thenReturn(Arrays.asList());


        mockMvc.perform(get("/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}