package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.dto.OtpVerificationRequest;
import com.example.taskmanagerapi.dto.UserRegistrationRequest;
import com.example.taskmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> registration(@RequestBody UserRegistrationRequest request) {
        Map<String, Object> response = userService.registration(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody OtpVerificationRequest request) {
        userService.verifyOtp(request.getEmail(), request.getOtp());
        return ResponseEntity.ok("Email verified successfully!");
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        userService.resendOtp(email);
        return ResponseEntity.ok("OTP resent successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        try {
            String token = userService.login(loginDto);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(Map.of("error", ex.getMessage()));
        }
    }

}
