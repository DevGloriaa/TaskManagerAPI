package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserEmail(String userEmail);

    List<Task> findByUserEmailAndCompleted(String userEmail, Boolean completed);

    List<Task> findByUserEmailAndPriority(String userEmail, String priority);

    List<Task> findByUserEmailAndDueDate(String userEmail, LocalDate dueDate);

    List<Task> findByUserEmailAndCompletedAndPriority(String userEmail, Boolean completed, String priority);

    List<Task> findByUserEmailAndCompletedAndDueDate(String userEmail, Boolean completed, LocalDate dueDate);

    List<Task> findByUserEmailAndPriorityAndDueDate(String userEmail, String priority, LocalDate dueDate);

    List<Task> findByUserEmailAndCompletedAndPriorityAndDueDate(String userEmail, Boolean completed, String priority, LocalDate dueDate);
}

