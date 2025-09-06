package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task task, String userEmail) {
        task.setUserEmail(userEmail);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks(String userEmail) {
        return taskRepository.findByUserEmail(userEmail);
    }
    @Override
    public Task getTaskById(String taskId, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized");
        }

        return task;
    }

    @Override
    public Task updateTask(String taskId, Task task, String userEmail) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existingTask.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized");
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.isCompleted());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(String taskId, String userEmail) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!existingTask.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Unauthorized");
        }

        taskRepository.delete(existingTask);
    }
}
