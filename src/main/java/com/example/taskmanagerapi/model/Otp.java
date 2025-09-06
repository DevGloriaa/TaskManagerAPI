package com.example.taskmanagerapi.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Document(collection = "otps")
public class Otp {
    @Id

    private  String id;
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
}
