package com.api.task_management.task.service;

import com.api.task_management.task.model.TaskModel;
import com.api.task_management.task.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public ResponseEntity<TaskModel> addTask(TaskModel task){
        TaskModel savedTask = taskRepo.save(task);

        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

    public ResponseEntity<List<TaskModel>> getAllTask(){
        List<TaskModel> tasks = taskRepo.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    public ResponseEntity<List<TaskModel>> getAllTaskByUserId(String userId){
        List<TaskModel> tasks = taskRepo.findAllByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    public ResponseEntity<TaskModel> getTaskByTaskId(String taskId){
        TaskModel task = taskRepo.findById(taskId).orElse(null);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    public ResponseEntity<TaskModel> updateTask(String taskId, TaskModel task){
        TaskModel taskInfo = taskRepo.findById(taskId).orElse(null);
        if(taskInfo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(task.getTitle() != null) taskInfo.setTitle(task.getTitle());
        if(task.getDescription() != null) taskInfo.setDescription(task.getDescription());
        if(task.getStatus() != null) taskInfo.setStatus(task.getStatus());
        if(task.getDue_date() != null) taskInfo.setDue_date(task.getDue_date());
        if(task.getUserId() != null) taskInfo.setUserId(task.getUserId());
        System.out.println(taskInfo.toString());
        taskRepo.save(taskInfo);
        return new ResponseEntity<>(taskInfo, HttpStatus.OK);
    }

    public ResponseEntity<String> deleteTask(String taskId){
        TaskModel taskInfo = taskRepo.findById(taskId).orElse(null);
        if(taskInfo == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskRepo.delete(taskInfo);
        return new ResponseEntity<>("Task deleted", HttpStatus.OK);
    }

    public ResponseEntity<String> updateStatus(String taskId, TaskModel.Status status){
        TaskModel taskInfo = taskRepo.findById(taskId).orElse(null);
        if(taskInfo == null){
            return new ResponseEntity<>("Task not Found", HttpStatus.NOT_FOUND);
        }
//        System.out.println(TaskModel.Status.valueOf(status.toUpperCase()));
//        TaskModel.Status taskStatus = TaskModel.Status.valueOf(status.toUpperCase());
        taskInfo.setStatus(status);

        taskRepo.save(taskInfo);

        return new ResponseEntity<>("Task updated to " + status, HttpStatus.OK);

    }



}
