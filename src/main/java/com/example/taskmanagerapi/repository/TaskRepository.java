package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
