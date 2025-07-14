//
//package com.api.task_management.task.repository;
//
//import com.api.task_management.task.model.TaskModel;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@DataMongoTest
//public class TaskRepoTest {
//
//    @Autowired
//    private TaskRepo taskRepo;
//
//    @BeforeEach
//    void setUp() {
//        taskRepo.deleteAll();
//    }
//
//    @Test
//    public void testFindAllByUserId() {
//        TaskModel task = new TaskModel();
//        task.setUserId("user1");
//        taskRepo.save(task);
//
//        TaskModel task2 = new TaskModel();
//        task2.setUserId("user1");
//        taskRepo.save(task2);
//
//        TaskModel task3 = new TaskModel();
//        task3.setUserId("user3");
//        taskRepo.save(task3);
//
//        List<TaskModel> tasks =  taskRepo.findAllByUserId("user1");
//        assertEquals(2, tasks.size());
//    }
//
//    @AfterEach
//    void tearDown() {
//        taskRepo.deleteAll();
//    }
//}
