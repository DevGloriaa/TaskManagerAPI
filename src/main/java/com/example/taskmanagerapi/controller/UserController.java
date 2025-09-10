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
    public ResponseEntity<Map<String,Object>> verifyUser(@RequestBody OtpVerificationRequest request) {
        userService.verifyOtp(request.getEmail(), request.getOtp());
        Map<String,Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Email verified successfully!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<Map<String,Object>> resendOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Map<String, Object> response = new HashMap<>();
        try {
            userService.resendOtp(email);
            response.put("success", true);
            response.put("message", "OTP resent successfully!");
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return ResponseEntity.ok(response);
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
