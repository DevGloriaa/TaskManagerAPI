package com.example.taskmanagerapi.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {
    @Id

    private String id;
    @NotBlank
    @Indexed(unique = true)
    private String username;

    @NotBlank
    @Indexed(unique = true)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 10)
    private String password;

    boolean isverified = true;
}
