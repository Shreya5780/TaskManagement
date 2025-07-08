package com.api.task_management.task.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document("tasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class TaskModel {
    public enum Status {
        TODO,
        IN_PROGRESS,
        DONE

    }

    @Id
    private String tid;

    @NotBlank(message = "title is required")
    private String title;

    private String description;

    private Status status = Status.TODO; //by default todo

    private LocalDate due_date;

    private String userId;

    @CreatedDate
    private LocalDateTime created_at;

    @LastModifiedDate
    private LocalDateTime updated_at;

}

/*
 id (primary key)
   title
   description
   status (TODO, IN_PROGRESS, DONE)
   due_date
   user_id (foreign key to Users)
   created_at
   updated_at
 */