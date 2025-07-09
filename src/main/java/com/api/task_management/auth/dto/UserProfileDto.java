package com.api.task_management.auth.dto;

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
    private String uid;
    @Email(message = "Enter valid email (abc@gmial.com)")
    private String email;

    @Size(min = 3, message = "Username must be atleast 3 characters ")
    private String username;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$",
            message = "Password must be at least 8 characters, contain one uppercase letter, and one special symbol"
    )
    private String password;
    private LocalDateTime created_at;
}
