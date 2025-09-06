package com.example.taskmanagerapi.dto;

import lombok.Data;

@Data
public class OtpRequest {
    private String email;
    private String otp;
}
