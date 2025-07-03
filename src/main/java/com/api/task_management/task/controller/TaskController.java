package com.api.task_management.task.controller;

import com.api.task_management.auth.dto.UserPrincipal;
import com.api.task_management.task.model.TaskModel;
import com.api.task_management.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<TaskModel> addTask(@RequestBody @Valid TaskModel task) {

        return taskService.addTask(task);

    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskModel>> getAllTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String userID = user.getUserId();
//        System.out.println("userID: " + userID);
//        return taskService.getAllTask();
        return taskService.getAllTaskByUserId(userID);
    }

    @GetMapping("/tasks/{taskid}")
    public ResponseEntity<TaskModel> getTaskById(@PathVariable String taskid) {
        return taskService.getTaskByTaskId(taskid);
    }

    @PutMapping("/tasks/{taskid}")
    public ResponseEntity<TaskModel> updateTask(@PathVariable String taskid, @RequestBody TaskModel task) {
        return taskService.updateTask(taskid, task);
    }

    @DeleteMapping("/tasks/{taskid}")
    public ResponseEntity<String> deleteTask(@PathVariable String taskid) {
        return taskService.deleteTask(taskid);
    }
}
