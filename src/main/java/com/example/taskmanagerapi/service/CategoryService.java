package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.model.Category;

import java.util.List;

public interface CategoryService {
     Category createCategory(Category category, String userEmail);
     List<Category> getCategories(String userEmail);
    public Category getCategoryByName(String categoryName, String userEmail);
    public Category getCategoryById(String categoryId, String userEmail);
     void deleteCategory(String categoryId,  String userEmail);
}
