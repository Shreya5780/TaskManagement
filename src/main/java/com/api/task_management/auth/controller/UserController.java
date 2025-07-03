package com.api.task_management.auth.controller;


import com.api.task_management.auth.dto.LoginModel;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody @Valid UserModel user) {
//
        return userService.register(user);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginModel user) {

        System.out.println(userService.login(user));
        return userService.login(user);
    }


}

