package com.example.taskmanagerapi.service;


import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.dto.UserRegistrationRequest;
import java.util.Map;

public interface UserService {
    Map<String, Object> registration(UserRegistrationRequest request);
    boolean verifyOtp(String email, String otpCode);
    String login(LoginDto loginDto);
    boolean resendOtp(String email);

}
