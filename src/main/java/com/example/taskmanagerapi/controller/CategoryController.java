package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.CategoryRequest;
import com.example.taskmanagerapi.model.Category;
import com.example.taskmanagerapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/{userEmail}")
    public ResponseEntity<Category> createCategory(@PathVariable String userEmail,
                                                   @RequestBody CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        return ResponseEntity.ok(categoryService.createCategory(category, userEmail));
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<List<Category>> getCategories(@PathVariable String userEmail) {
        return ResponseEntity.ok(categoryService.getCategories(userEmail));
    }
    @GetMapping("/{userEmail}/name/{categoryName}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String userEmail,
                                                      @PathVariable String categoryName) {
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName, userEmail));
    }
    @GetMapping("/{userEmail}/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String userEmail,
                                                    @PathVariable String categoryId) {
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId, userEmail));
    }
    @DeleteMapping("/{userEmail}/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String userEmail,
                                               @PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId, userEmail);
        return ResponseEntity.noContent().build();
    }

}
