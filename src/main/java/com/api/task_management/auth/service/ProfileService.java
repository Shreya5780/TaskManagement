package com.api.task_management.auth.service;

import com.api.task_management.auth.dto.UserProfileDto;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.repository.UserRepo;
import com.api.task_management.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public ResponseEntity<UserProfileDto> getUserByUsername(String username) {
        UserModel user = userRepo.findByUsername(username);

        UserProfileDto userInfo = new UserProfileDto(user.getUid(), user.getEmail(), user.getUsername(), user.getPassword(), user.getCreated_at());

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    public ResponseEntity<UserModel> updateUser(String username, UserProfileDto user) {
        UserModel userModel = userRepo.findByUsername(username);

        //to avoid user can't update with exisiting one
        if(userRepo.existsByEmail(user.getEmail()) && (!userModel.getEmail().equalsIgnoreCase(user.getEmail()))) {
            throw new UserAlreadyExistException(user.getEmail() + " is already exist, so you don't update it");
        }
//        if(userRepo.existsByUsername(user.getUsername()) && (!userModel.getUsername().equalsIgnoreCase(user.getUsername()))) {
//            throw new UserAlreadyExistException(user.getUsername()  + " is already exist, so you don't update it"   );
//        }

        //if field vallue is there then set else as it is
        if(!user.getEmail().isBlank()) userModel.setEmail(user.getEmail());
//        if(user.getUsername() != null) userModel.setUsername(user.getUsername());
        if(!user.getPassword().isBlank()) userModel.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepo.save(userModel);

        return new ResponseEntity<>(userModel,  HttpStatus.OK);
    }

    public ResponseEntity<List<UserProfileDto>> getAllUser() {
         List<UserModel> users = userRepo.findAll();
         List<UserProfileDto> userInfo = new ArrayList<>();
         for(UserModel user : users) {
             UserProfileDto dto =  new UserProfileDto(user.getUid(), user.getEmail(), user.getUsername(), user.getPassword(), user.getCreated_at());
             userInfo.add(dto);

         }

         return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }
}
