package com.example.taskmanagerapi.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskFilterRequest {
    private String userEmail;
    private Boolean completed;
    private String priority;
    private LocalDate dueDate;

}
