package com.api.task_management.task.repository;

import com.api.task_management.task.model.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends MongoRepository<TaskModel, String> {
    List<TaskModel> findAllByUserId(String userId);

    void deleteByTitle(String title);
}
