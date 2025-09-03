package com.example.taskmanagerapi.model;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tasks")
public class Task {
    @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)


    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String title;

    @NotBlank
    private String description;
    private String status = "pending";
}
