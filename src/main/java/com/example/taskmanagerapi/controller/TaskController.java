package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.TaskFilterRequest;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private final TaskRepository taskRepository;
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/createtask")
    public ResponseEntity<Task> createTask(@RequestBody Task task, HttpServletRequest request) {
        String userEmail = (String) request.getUserPrincipal().getName();
        return ResponseEntity.ok(taskService.createTask(task, userEmail));
    }

    @GetMapping("/getalltasks")
    public ResponseEntity<List<Task>> getTasks(HttpServletRequest request) {
        String userEmail = (String) request.getUserPrincipal().getName();
        return ResponseEntity.ok(taskService.getTasks(userEmail));
    }
    @GetMapping("/gettask{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id, HttpServletRequest request) {
        String userEmail = (String) request.getUserPrincipal().getName();
        Task task = taskService.getTaskById(id, userEmail);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/updatetask{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task, HttpServletRequest request) {
        String userEmail = (String) request.getUserPrincipal().getName();
        return ResponseEntity.ok(taskService.updateTask(id, task, userEmail));
    }

    @DeleteMapping("/deletetask{id}")
    public ResponseEntity<String> deleteTask(@PathVariable String id, HttpServletRequest request) {
        String userEmail = (String) request.getUserPrincipal().getName();
        taskService.deleteTask(id, userEmail);
        return ResponseEntity.ok("Task deleted successfully!");
    }
    @PostMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestBody TaskFilterRequest filterRequest) {
        List<Task> tasks = taskService.searchTasks(filterRequest);
        return ResponseEntity.ok(tasks);
    }


}
