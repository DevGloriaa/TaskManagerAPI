package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.dto.OtpVerificationRequest;
import com.example.taskmanagerapi.dto.UserRegistrationRequest;
import com.example.taskmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
        userService.registration(request);
        return ResponseEntity.ok("User registered successfully! OTP sent to email.");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody OtpVerificationRequest request) {
        userService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok("Email verified successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
        boolean success = userService.login(loginDto);
        if (success) return ResponseEntity.ok("Login successful!");
        return ResponseEntity.status(401).body("Invalid credentials!");
    }
}
