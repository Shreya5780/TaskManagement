package com.api.task_management.auth.controller;


import com.api.task_management.auth.dto.LoginModel;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.service.UserService;
import com.api.task_management.config.SecurityConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody @Valid UserModel user) throws Exception {
        String encryptedPassword = user.getPassword();

        user.setPassword(userService.decryptPassword(encryptedPassword));
        return userService.register(user);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody @Valid LoginModel user) throws Exception {

        String encryptedPassword = user.getPassword();

        user.setPassword(userService.decryptPassword(encryptedPassword));
        System.out.println(userService.login(user));
        return userService.login(user);
    }

    @GetMapping("/public-key")
    public ResponseEntity<String> getPublicKey() throws Exception {

        return new ResponseEntity<>(userService.publicKey(), HttpStatus.OK);

    }

    @GetMapping("/encrypt")
    public ResponseEntity<String> getEncryptPassword(@RequestParam String password) throws Exception {
        return new ResponseEntity<>(userService.encryptPassword(password), HttpStatus.OK);
    }

}

