package com.api.task_management.auth.service;

import com.api.task_management.auth.model.UserModel;
import com.api.task_management.auth.dto.UserPrincipal;
import com.api.task_management.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService  implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserModel user = userRepo.findByUsername(username);
        if(user == null){

            throw new UsernameNotFoundException("Username not found");
        }

        return new UserPrincipal(user);
    }
}
