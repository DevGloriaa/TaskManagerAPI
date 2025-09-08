package com.example.taskmanagerapi.dto;

import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String categoryId;
}
