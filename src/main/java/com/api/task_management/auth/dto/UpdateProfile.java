package com.api.task_management.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class UpdateProfile {
    @Email(message = "Enter valid email (abc@gmial.com)")
    private String email;

    private String password;
}



