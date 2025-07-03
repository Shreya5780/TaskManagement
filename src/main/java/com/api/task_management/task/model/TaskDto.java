package com.api.task_management.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class TaskDto {
    private String id;
    private String title;
    private String description;
    private TaskModel.Status status;
    private String user_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
