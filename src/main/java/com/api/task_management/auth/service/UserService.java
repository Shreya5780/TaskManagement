package com.api.task_management.auth.service;


import com.api.task_management.auth.dto.LoginModel;
import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.repository.UserRepo;
import com.api.task_management.config.SecurityConfig;
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

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.util.Base64;
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

    @Autowired
    private SecurityConfig securityConfig;

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
        if (userModel == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUsername(), user.getPassword()));

        String token = jWTService.generateToken(userModel.getUsername(), userModel.getUid());
        String userId = userModel.getUid();
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", userId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    public String  encryptPassword(String password) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec(


                "SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT

        );

        cipher.init(Cipher.ENCRYPT_MODE, securityConfig.getPublicKey(), oaepParameterSpec);

//        cipher.init(Cipher.DECRYPT_MODE, securityConfig.getPrivateKey());

        byte[] encryptedPassword = cipher.doFinal(password.getBytes("UTF-8"));
        String encryptedString = Base64.getEncoder().encodeToString(encryptedPassword);

        return  encryptedString;
    }


    public String decryptPassword(String encryptedPassword) throws Exception {
        System.out.println(encryptedPassword);
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec(


                "SHA-256",
                "MGF1",
                MGF1ParameterSpec.SHA256,
                PSource.PSpecified.DEFAULT

        );

        cipher.init(Cipher.DECRYPT_MODE, securityConfig.getPrivateKey(), oaepParameterSpec);

//        cipher.init(Cipher.DECRYPT_MODE, securityConfig.getPrivateKey());

        byte[] decryptedPassword = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        String decryptedPasswordString = new String(decryptedPassword, "UTF-8");

        return  decryptedPasswordString;

    }

    public String publicKey() throws Exception {
        PublicKey publicKey = securityConfig.getPublicKey();

        String encodedKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        return  encodedKey;
    }
}
