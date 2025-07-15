//
//package com.api.task_management.task.controller;
//
//import com.api.task_management.task.model.TaskModel;
//import com.api.task_management.task.service.TaskService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(TaskController.class)
//public class TaskControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TaskService taskService;
//
//    @Test
//    public void testAddTask() throws Exception {
//        when(taskService.addTask(any(TaskModel.class))).thenReturn(null);
//
//        mockMvc.perform(post("/tasks")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetAllTask() throws Exception {
//        when(taskService.getAllTask()).thenReturn(null);
//
//        mockMvc.perform(get("/tasks"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetAllTaskByUserId() throws Exception {
//        when(taskService.getAllTaskByUserId("user1")).thenReturn(null);
//
//        mockMvc.perform(get("/tasks/user/user1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testGetTaskByTaskId() throws Exception {
//        when(taskService.getTaskByTaskId("task1")).thenReturn(null);
//
//        mockMvc.perform(get("/tasks/task1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testUpdateTask() throws Exception {
//        when(taskService.updateTask(anyString(), any(TaskModel.class))).thenReturn(null);
//
//        mockMvc.perform(put("/tasks/task1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteTask() throws Exception {
//        when(taskService.deleteTask("task1")).thenReturn(null);
//
//        mockMvc.perform(delete("/tasks/task1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testUpdateStatus() throws Exception {
//        when(taskService.updateStatus(anyString(), any(TaskModel.Status.class))).thenReturn(null);
//
//        mockMvc.perform(put("/tasks/task1/COMPLETED"))
//                .andExpect(status().isOk());
//    }
//}
