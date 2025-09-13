package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.model.Category;
import com.example.taskmanagerapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category, HttpServletRequest request) {
        String userEmail = request.getUserPrincipal().getName();
        return ResponseEntity.ok(categoryService.createCategory(category, userEmail));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Category>> getCategories(HttpServletRequest request) {
        String userEmail = request.getUserPrincipal().getName();
        return ResponseEntity.ok(categoryService.getCategories(userEmail));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable String id, HttpServletRequest request) {
        String userEmail = request.getUserPrincipal().getName();
        return ResponseEntity.ok(categoryService.getCategoryById(id, userEmail));
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name, HttpServletRequest request) {
        String userEmail = request.getUserPrincipal().getName();
        return ResponseEntity.ok(categoryService.getCategoryByName(name, userEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id, HttpServletRequest request) {
        String userEmail = request.getUserPrincipal().getName();
        categoryService.deleteCategory(id, userEmail);
        return ResponseEntity.ok("Category deleted successfully.");
    }
}
