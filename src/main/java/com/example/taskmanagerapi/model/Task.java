package com.example.taskmanagerapi.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String title;

    @NotBlank
    private String description;

    private boolean completed;
    private String priority;
    private LocalDate dueDate;

    private String userEmail;
    private String categoryId;
}
