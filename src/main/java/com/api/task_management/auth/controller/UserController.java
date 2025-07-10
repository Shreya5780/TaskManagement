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

    @Autowired
    private SecurityConfig securityConfig;

    @PostMapping("/register")
    public ResponseEntity<UserModel> register(@RequestBody @Valid UserModel user) {
//
        return userService.register(user);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginModel user) throws Exception {

        String encryptedPassword = user.getPassword();
//        System.out.println(encryptedPassword);
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec(


               "SHA-256",
                 "MGF1",
                 MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT

        );

        cipher.init(Cipher.DECRYPT_MODE, securityConfig.getPrivateKey(), oaepParameterSpec);

//        cipher.init(Cipher.DECRYPT_MODE, securityConfig.getPrivateKey(), oaepParameterSpec);

        byte[] decryptedPassword = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        String decryptedPasswordString = new String(decryptedPassword, "UTF-8");

        user.setPassword(decryptedPasswordString);
        System.out.println(userService.login(user));
        return userService.login(user);
    }

    @GetMapping("/public-key")
    public ResponseEntity<String> getPublicKey() throws Exception {
        PublicKey publicKey = securityConfig.getPublicKey();

        String encodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        System.out.println(encodedKey);
        return new ResponseEntity<>(encodedKey, HttpStatus.OK);

    }

    @GetMapping("/key")
    public ResponseEntity<String> privateKey() throws Exception {
        PrivateKey publicKey = securityConfig.getPrivateKey();

        String encodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        return new ResponseEntity<>(encodedKey, HttpStatus.OK);

    }


}

