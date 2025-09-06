package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserEmail(String email);
}
