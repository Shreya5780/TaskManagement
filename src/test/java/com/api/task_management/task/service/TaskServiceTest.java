package com.api.task_management.task.service;

import com.api.task_management.task.model.TaskModel;
import com.api.task_management.task.repository.TaskRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Loads the full Spring application context
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// After each test method, Spring will reset the context, so each test starts fresh
public class TaskServiceTest {

    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    private TaskService taskService;

    @Test
    public void testAddTask() {
        TaskModel task = new TaskModel();
        // Optionally set task fields here
        task.setTid("123");
        task.setTitle("title");
        task.setDescription("description");
        ResponseEntity<TaskModel> response = taskService.addTask(task);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        // Optionally check that the task is saved in the database
        assertTrue(taskRepo.existsById("123"));
    }

    @Test
    public void testGetAllTask() {
        // Setup: Add some tasks to the repo
        TaskModel task1 = new TaskModel();
        TaskModel task2 = new TaskModel();
        taskRepo.save(task1);
        taskRepo.save(task2);

        ResponseEntity<List<TaskModel>> response = taskService.getAllTask();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() >= 2); // At least the two we added
    }

    @Test
    public void testGetAllTaskByUserId() {
        TaskModel task = new TaskModel();
        task.setUserId("user1");
        taskRepo.save(task);

        ResponseEntity<List<TaskModel>> response = taskService.getAllTaskByUserId("user1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    public void testGetTaskByTaskId() {
        TaskModel task = new TaskModel();
        task.setTid("task1");
        taskRepo.save(task);

        ResponseEntity<TaskModel> response = taskService.getTaskByTaskId("task1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("task1", response.getBody().getTid());
    }

    @Test
    public void testUpdateTask() {
        TaskModel task = new TaskModel();
        task.setTid("task1");
        taskRepo.save(task);

        TaskModel updatedTask = new TaskModel();
        // Set updated fields here

        ResponseEntity<TaskModel> response = taskService.updateTask("task1", updatedTask);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteTask() {
        TaskModel task = new TaskModel();
        task.setTid("task1");
        taskRepo.save(task);

        ResponseEntity<String> response = taskService.deleteTask("task1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Task deleted", response.getBody());
        assertFalse(taskRepo.findById("task1").isPresent());
    }

    @Test
    public void testUpdateStatus() {
        TaskModel task = new TaskModel();
        task.setTid("task1");
        taskRepo.save(task);

        ResponseEntity<String> response = taskService.updateStatus("task1", TaskModel.Status.DONE);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Task updated to DONE", response.getBody());
    }


}




//
//package com.api.task_management.task.service;
//
//import com.api.task_management.task.model.TaskModel;
//import com.api.task_management.task.repository.TaskRepo;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TaskServiceTest {
//
//    @Mock
//    private TaskRepo taskRepo;
//
//    @InjectMocks
//    private TaskService taskService;
//
//    //checks the response is OK and contains the task
//    @Test
//    public void testAddTask() {
//        TaskModel task = new TaskModel();
//        when(taskRepo.save(task)).thenReturn(task);
//
//        ResponseEntity<TaskModel> response = taskService.addTask(task);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(task, response.getBody());
//    }
//
//    //Checks if getting all tasks returns the correct list.
//    @Test
//    public void testGetAllTask() {
//        List<TaskModel> tasks = new ArrayList<>();
//        when(taskRepo.findAll()).thenReturn(tasks);
//
//        ResponseEntity<List<TaskModel>> response = taskService.getAllTask();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(tasks, response.getBody());
//    }
//
//    //get task list by userid
//    @Test
//    public void testGetAllTaskByUserId() {
//        List<TaskModel> tasks = new ArrayList<>();
//        when(taskRepo.findAllByUserId("user1")).thenReturn(tasks);
//
//        ResponseEntity<List<TaskModel>> response = taskService.getAllTaskByUserId("user1");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(tasks, response.getBody());
//    }
//
//    //task by taskid
//    @Test
//    public void testGetTaskByTaskId() {
//        TaskModel task = new TaskModel();
//        when(taskRepo.findById("task1")).thenReturn(Optional.of(task));
//
//        ResponseEntity<TaskModel> response = taskService.getTaskByTaskId("task1");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(task, response.getBody());
//    }
//
//    //update task
//    @Test
//    public void testUpdateTask() {
//        TaskModel task = new TaskModel();
//        when(taskRepo.findById("task1")).thenReturn(Optional.of(task));
//
//        ResponseEntity<TaskModel> response = taskService.updateTask("task1", new TaskModel());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    //delete task
//    @Test
//    public void testDeleteTask() {
//        TaskModel task = new TaskModel();
//        when(taskRepo.findById("task1")).thenReturn(Optional.of(task));
//
//        ResponseEntity<String> response = taskService.deleteTask("task1");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Task deleted", response.getBody());
//    }
//
//    //update status
//    @Test
//    public void testUpdateStatus() {
//        TaskModel task = new TaskModel();
//        when(taskRepo.findById("task1")).thenReturn(Optional.of(task));
//
//        ResponseEntity<String> response = taskService.updateStatus("task1", TaskModel.Status.DONE);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Task updated to DONE", response.getBody());
//    }
//}
