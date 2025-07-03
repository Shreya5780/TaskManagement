package com.api.task_management.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class UserProfileDto {
    private String uid;
    private String email;
    private String username;
    private String password;
    private Date created_at;
}
