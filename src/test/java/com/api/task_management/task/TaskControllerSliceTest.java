
package com.api.task_management.task;

import com.api.task_management.auth.service.JWTService;
import com.api.task_management.task.controller.TaskController;
import com.api.task_management.task.model.TaskModel;
import com.api.task_management.task.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
web slice testing on controller
with MockMVC framework

 */
@WebMvcTest(TaskController.class)

//to unable testing without authorization, if not then give 403 forbidden error
@AutoConfigureMockMvc(addFilters = false)

public class TaskControllerSliceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TaskService taskService;

    @MockitoBean
    private JWTService jwtService;

    @Test
    @WithMockUser
    public void addTask() throws Exception {
        TaskModel task = new TaskModel();
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk());
    }
}
