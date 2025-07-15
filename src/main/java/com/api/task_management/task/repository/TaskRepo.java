package com.api.task_management.task.repository;

import com.api.task_management.task.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskModel, String> {
    List<TaskModel> findAllByUserId(int userId);

    void deleteByTitle(String title);
}
