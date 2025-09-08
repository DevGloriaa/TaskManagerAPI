package com.example.taskmanagerapi.controller;


import com.example.taskmanagerapi.dto.TaskFilterRequest;
import com.example.taskmanagerapi.dto.TaskRequest;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/createtask")
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest request,
                                           HttpServletRequest httpRequest) {
        String userEmail = httpRequest.getUserPrincipal().getName();
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setCompleted(request.isCompleted());
        task.setDueDate(request.getDueDate());
        task.setCategoryId(request.getCategoryId());
        task.setUserEmail(userEmail);

        return ResponseEntity.ok(taskService.createTask(task, userEmail));
    }

    @GetMapping("/gettasks")
    public ResponseEntity<List<Task>> getTasks(HttpServletRequest httpRequest) {
        String userEmail = httpRequest.getUserPrincipal().getName();
        return ResponseEntity.ok(taskService.getTasks(userEmail));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id,
                                            HttpServletRequest httpRequest) {
        String userEmail = httpRequest.getUserPrincipal().getName();
        return ResponseEntity.ok(taskService.getTaskById(id, userEmail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id,
                                           @RequestBody Task task,
                                           HttpServletRequest httpRequest) {
        String userEmail = httpRequest.getUserPrincipal().getName();
        return ResponseEntity.ok(taskService.updateTask(id, task, userEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id,
                                             HttpServletRequest httpRequest) {
        String userEmail = httpRequest.getUserPrincipal().getName();
        taskService.deleteTask(id, userEmail);
        return ResponseEntity.ok("Task deleted successfully!");
    }


    @PostMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestBody TaskFilterRequest filterRequest) {
        return ResponseEntity.ok(taskService.searchTasks(filterRequest));
    }
}
