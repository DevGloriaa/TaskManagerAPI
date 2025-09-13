package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Category> findByNameAndUserEmail(String name, String userEmail);
    List<Category> findByUserEmail(String userEmail);
}
