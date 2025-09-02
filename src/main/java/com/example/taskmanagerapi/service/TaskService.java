package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.model.Task;
import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(String id);
    Task createTask(Task task);
    Task updateTask(String id, Task updatedTask);
    void deleteTask(String id);
}
