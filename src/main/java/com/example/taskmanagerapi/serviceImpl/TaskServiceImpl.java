package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);

    }

    @Override
    public Task updateTask(String id, Task updatedTask) {
       Task task = getTaskById(id);
       task.setTitle(updatedTask.getTitle());
       task.setDescription(updatedTask.getDescription());
       task.setStatus(updatedTask.getStatus());
       return taskRepository.save(task);
    }

    @Override
    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: "+id);
        }
        taskRepository.deleteById(id);
    }
}
