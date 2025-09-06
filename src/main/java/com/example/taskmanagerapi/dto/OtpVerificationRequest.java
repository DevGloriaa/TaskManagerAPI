package com.example.taskmanagerapi.dto;

import lombok.Data;

@Data
public class OtpVerificationRequest {
    private String email;
    private String otp;
}
