package com.api.task_management.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    private int uid;
    @Email(message = "Enter valid email (abc@gmial.com)")
    private String email;

    @Size(min = 3, message = "Username must be atleast 3 characters ")
    private String username;

    private LocalDateTime created_at;
}


