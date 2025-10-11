package com.example.taskmanagerapi.service;

public interface PasswordResetService {
    String generateResetToken(String email);
    String resetPassword(String token, String newPassword);

}
