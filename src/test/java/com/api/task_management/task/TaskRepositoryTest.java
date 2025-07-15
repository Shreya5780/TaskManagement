
package com.api.task_management.task;

import com.api.task_management.task.model.TaskModel;
import com.api.task_management.task.repository.TaskRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

//@DataMongoTest
//this is mongo slice testing
@SpringBootTest
@ActiveProfiles("test")
public class TaskRepositoryTest {

    @Autowired
    private TaskRepo taskRepository;

    @AfterEach
    void tearDown() {
        taskRepository.deleteByTitle("Test Task");
    }

    @Test
    public void addTask() {
        TaskModel task = new TaskModel();
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        TaskModel savedTask = taskRepository.save(task);

        assertThat(savedTask.getTid()).isNotNull();
        assertThat(savedTask.getTitle()).isEqualTo("Test Task");
    }


}
