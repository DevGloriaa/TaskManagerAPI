package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.TaskFilterRequest;
import com.example.taskmanagerapi.model.Task;

import java.util.List;
public interface TaskService {
    Task createTask(Task task, String userEmail);
    List<Task> getTasks(String userEmail);
    Task getTaskById(String taskId, String userEmail);
    Task updateTask(String taskId, Task task, String userEmail);
    List<Task> getTasksByCategory(String categoryId, String userEmail);
    List<Task> getTasksByCategoryName(String categoryName, String userEmail);
    Task markTaskAsComplete(String taskId, String userEmail);
    void deleteTask(String taskId, String userEmail);
    List<Task> searchTasks(TaskFilterRequest filterRequest);
    Task toggleComplete(String taskId, String userEmail);
    String exportTasksToICS(String userEmail);



}
