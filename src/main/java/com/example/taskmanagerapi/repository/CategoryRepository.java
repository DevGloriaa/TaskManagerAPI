package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByUserEmail(String userEmail);

}
