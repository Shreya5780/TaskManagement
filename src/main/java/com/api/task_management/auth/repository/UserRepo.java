package com.api.task_management.auth.repository;

import com.api.task_management.auth.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepo extends MongoRepository<UserModel,String> {
    UserModel findByEmail(String email);

    UserModel findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

//    UserModel findByEmailOrUsername(String email, String username);
}
