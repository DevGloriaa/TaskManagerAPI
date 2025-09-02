package com.example.taskmanagerapi.controller;


import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getalltasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/gettask{id}")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id);
    }
    @PostMapping("/createtask")
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/updatetask{id}")
    public Task updateTask(@PathVariable String id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }
    @DeleteMapping("/deletetask{id}")
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }
}
