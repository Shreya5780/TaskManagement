package com.api.task_management.auth.controller;

import com.api.task_management.auth.dto.UserProfileDto;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.service.ProfileService;
import com.api.task_management.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return profileService.getUserByUsername(username);

    }

    @PutMapping("/me")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserProfileDto user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return profileService.updateUser(username, user);
    }


}
