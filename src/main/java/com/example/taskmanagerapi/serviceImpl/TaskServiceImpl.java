package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.dto.TaskFilterRequest;
import com.example.taskmanagerapi.model.Category;
import com.example.taskmanagerapi.model.Task;
import com.example.taskmanagerapi.repository.CategoryRepository;
import com.example.taskmanagerapi.repository.TaskRepository;
import com.example.taskmanagerapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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
    public List<Task> getTodayTasks(String userEmail) {
        LocalDate today = LocalDate.now();

        Date startOfDay = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return taskRepository.findByUserEmailAndDueDateBetween(userEmail, startOfDay, endOfDay);
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
        Task existingTask = getTaskById(taskId, userEmail);

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setCompleted(task.isCompleted());
        existingTask.setDueDate(task.getDueDate());

        return taskRepository.save(existingTask);
    }
    @Override
    public List<Task> getTasksByCategory(String categoryId, String userEmail) {
        return taskRepository.findByCategoryIdAndUserEmail(categoryId, userEmail);
    }


    @Override
    public List<Task> getTasksByCategoryName(String categoryName, String userEmail) {

        Category category = categoryRepository.findByNameAndUserEmail(categoryName, userEmail)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return taskRepository.findByCategoryIdAndUserEmail(category.getId(), userEmail);
    }
    @Override
    public Task markTaskAsComplete(String taskId, String userEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUserEmail().equals(userEmail)) {
            throw new RuntimeException("Forbidden: You can only complete your own tasks.");
        }

        task.setCompleted(true);
        return taskRepository.save(task);
    }

    @Override
    public Task toggleComplete(String taskId, String userEmail) {
        Task task = getTaskById(taskId, userEmail);
        task.setCompleted(!task.isCompleted());
        return taskRepository.save(task);
    }
    @Override
    public String exportTasksToICS(String userEmail) {
        List<Task> tasks = taskRepository.findByUserEmail(userEmail);

        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCALENDAR\n");
        sb.append("VERSION:2.0\n");
        sb.append("PRODID:-//Optimus Task Manager//EN\n");

        for (Task task : tasks) {
            sb.append("BEGIN:VEVENT\n");
            sb.append("SUMMARY:").append(task.getTitle()).append("\n");
            sb.append("DESCRIPTION:").append(task.getDescription()).append("\n");

            if (task.getDueDate() != null) {
                sb.append("DTSTART:").append(task.getDueDate().atTime(6, 0).toString().replace("-", "").replace(":", "")).append("Z\n");
                sb.append("DTEND:").append(task.getDueDate().atTime(23, 59).toString().replace("-", "").replace(":", "")).append("Z\n");
            }

            sb.append("END:VEVENT\n");
        }

        sb.append("END:VCALENDAR");
        return sb.toString();
    }




    @Override
    public void deleteTask(String taskId, String userEmail) {
        Task existingTask = getTaskById(taskId, userEmail);
        taskRepository.delete(existingTask);
    }

    @Override
    public List<Task> searchTasks(TaskFilterRequest filterRequest) {
        String userEmail = filterRequest.getUserEmail();
        Boolean completed = filterRequest.getCompleted();
        String priority = filterRequest.getPriority();
        LocalDate dueDate = filterRequest.getDueDate();


        Date startOfDay = null;
        Date endOfDay = null;
        if (dueDate != null) {
            startOfDay = Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            endOfDay = Date.from(dueDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if (completed != null && priority != null && dueDate != null) {
            return taskRepository.findByUserEmailAndCompletedAndPriorityAndDueDateBetween(userEmail, completed, priority, startOfDay, endOfDay);
        } else if (completed != null && priority != null) {
            return taskRepository.findByUserEmailAndCompletedAndPriority(userEmail, completed, priority);
        } else if (completed != null && dueDate != null) {
            return taskRepository.findByUserEmailAndCompletedAndDueDateBetween(userEmail, completed, startOfDay, endOfDay);
        } else if (priority != null && dueDate != null) {
            return taskRepository.findByUserEmailAndPriorityAndDueDateBetween(userEmail, priority, startOfDay, endOfDay);
        } else if (completed != null) {
            return taskRepository.findByUserEmailAndCompleted(userEmail, completed);
        } else if (priority != null) {
            return taskRepository.findByUserEmailAndPriority(userEmail, priority);
        } else if (dueDate != null) {
            return taskRepository.findByUserEmailAndDueDateBetween(userEmail, startOfDay, endOfDay);
        } else {
            return taskRepository.findByUserEmail(userEmail);
        }
    }
}
