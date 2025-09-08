package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.model.Category;
import com.example.taskmanagerapi.repository.CategoryRepository;
import com.example.taskmanagerapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, String userEmail) {
        category.setUserEmail(userEmail);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getCategories(String userEmail) {
        return categoryRepository.findByUserEmail(userEmail);
    }

    @Override
    public Category getCategoryByName(String categoryName, String userEmail) {
        return categoryRepository.findByNameAndUserEmail(categoryName, userEmail)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Category getCategoryById(String categoryId, String userEmail) {
        return categoryRepository.findById(categoryId)
                .filter(c -> c.getUserEmail().equals(userEmail))
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
    @Override
    public void deleteCategory(String categoryId, String userEmail) {
        Category category = getCategoryById(categoryId, userEmail);
        categoryRepository.delete(category);
    }
}
