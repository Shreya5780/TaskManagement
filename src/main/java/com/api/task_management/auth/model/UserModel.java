package com.api.task_management.auth.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Document("users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    @Id
    private String uid;

    @Indexed(unique = true)
    @NotBlank(message = "Email is required")
    @Email(message = "Enter valid email (abc@gmial.com)")
    private String email;

    @NotBlank(message = "Username is required")
    @Size(min = 3, message = "Username must be atleast 3 characters ")
    private String username;

    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$",
            message = "Password must be at least 8 characters, contain one uppercase letter, and one special symbol"
    )
    private String password;

    @CreatedDate
    private LocalDateTime created_at;

}
/*
@NotNull - If email is present but empty (""), this will not trigger a validation error.
- just field name there no error

@NotBlank - If username is " " or "", this will trigger a validation error.
- field name and must have some valid value, not even white space

@NotEmpty - If password is "", this will trigger a validation error. If it's " ", it won’t.
- field name and must have input even white space
 */
