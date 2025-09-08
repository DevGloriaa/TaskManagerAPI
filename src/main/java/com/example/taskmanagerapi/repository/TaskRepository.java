package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {


    List<Task> findByUserEmail(String userEmail);

    List<Task> findByUserEmailAndCompleted(String userEmail, Boolean completed);

    List<Task> findByUserEmailAndPriority(String userEmail, String priority);


    List<Task> findByUserEmailAndDueDateBetween(String userEmail, Date start, Date end);

    List<Task> findByUserEmailAndCompletedAndDueDateBetween(String userEmail, Boolean completed, Date start, Date end);

    List<Task> findByUserEmailAndPriorityAndDueDateBetween(String userEmail, String priority, Date start, Date end);

    List<Task> findByUserEmailAndCompletedAndPriorityAndDueDateBetween(String userEmail, Boolean completed, String priority, Date start, Date end);

    List<Task> findByUserEmailAndCompletedAndPriority(String userEmail, Boolean completed, String priority);
}
