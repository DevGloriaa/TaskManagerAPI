package com.example.taskmanagerapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String priority;
    private boolean completed;
    private LocalDate dueDate;
    private String categoryId;

}
