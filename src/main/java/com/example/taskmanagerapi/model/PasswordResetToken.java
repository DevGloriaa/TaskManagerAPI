package com.example.taskmanagerapi.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    private String id;
    private String token;
    private String email;
    private LocalDateTime expirationTime;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, String email, LocalDateTime expirationTime) {
        this.token = token;
        this.email = email;
        this.expirationTime = expirationTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }
}