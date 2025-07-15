package com.api.task_management.auth.controller;

import com.api.task_management.auth.dto.UpdateProfile;
import com.api.task_management.auth.dto.UserProfileDto;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.service.ProfileService;
import com.api.task_management.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for managing user profiles.
 * Provides endpoints to retrieve, update, and list user data.
 */

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getUser(@RequestParam int userId) {


        return profileService.getUserByUserid(userId);

    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileDto> updateUser(@RequestBody @Valid UpdateProfile user, @RequestParam int userId) throws Exception {
//        System.out.println(user.getUsername() +" "+ user.getEmail()+" "+ user.getPassword());
        String encryptedPassword = user.getPassword();

        user.setPassword(userService.decryptPassword(encryptedPassword));
        return profileService.updateUser(userId, user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserProfileDto>> getAllUsers() {
        return profileService.getAllUser();
    }


}
