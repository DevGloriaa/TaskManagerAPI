package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByUserEmail(String userEmail);
    Optional<Category> findByNameAndUserEmail(String name, String userEmail);
}

