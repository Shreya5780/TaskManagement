package com.api.task_management.auth.service;


import com.api.task_management.auth.dto.LoginModel;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.repository.UserRepo;
import com.api.task_management.exception.UserAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jWTService;

    public ResponseEntity<UserModel> register(UserModel user) {

        if(userRepo.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException(user.getEmail() + " is already exist");
        }
        if(userRepo.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistException(user.getUsername()  + " is already exist"   );
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setCreated_at(new Date());
        userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> login(LoginModel user) {
        String emailOrPassword = user.getEmailOrUsername();

        //check if email or username exist or not
        UserModel userModel = userRepo.findByEmail(emailOrPassword);
        if(userModel == null){
            userModel = userRepo.findByUsername(emailOrPassword);
        }
//        System.out.println(userModel.getEmail() + " " + userModel.getUsername()+   "  " +   userModel.getPassword()    + "user........");
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found");
//            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUsername(), user.getPassword()));

//        if(authentication.isAuthenticated()) {
        String token = jWTService.generateToken(userModel.getUsername(), userModel.getUid());
        String userId = userModel.getUid();
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
//        }

//        return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);

    }
}
