package com.api.task_management.auth.controller;

import com.api.task_management.auth.dto.UserProfileDto;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUser(@RequestParam String userId) {


        return profileService.getUserByUserid(userId);

    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileDto> updateUser(@RequestBody @Valid UserProfileDto user, @RequestParam String userId) throws BadCredentialsException {
//        System.out.println(user.getUsername() +" "+ user.getEmail()+" "+ user.getPassword());

        return profileService.updateUser(userId, user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return profileService.getAllUser();
    }


}
