package com.api.task_management.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class UserProfileDto {
    private String uid;
    private String email;
    private String username;
    private String password;
    private LocalDateTime created_at;
}
