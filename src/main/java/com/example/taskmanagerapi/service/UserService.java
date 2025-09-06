package com.example.taskmanagerapi.service;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.dto.UserRegistrationRequest;
import com.example.taskmanagerapi.model.User;

public interface UserService {
    String registration(UserRegistrationRequest request);
    boolean verifyOtp(String email, String otpCode);
    boolean login(LoginDto loginDto);
}
