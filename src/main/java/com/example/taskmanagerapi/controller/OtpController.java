package com.example.taskmanagerapi.controller;

import com.example.taskmanagerapi.dto.OtpRequest;
import com.example.taskmanagerapi.service.EmailService;
import com.example.taskmanagerapi.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpService otpService;
    private final EmailService emailService;

    @Autowired
    public OtpController(OtpService otpService, EmailService emailService) {
        this.otpService = otpService;
        this.emailService = emailService;
    }
    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generateOtp(@RequestBody OtpRequest request) {
        String email = request.getEmail();
        String otp = otpService.generateOtp(email);

        emailService.sendOtpEmail(email, otp);

        Map<String, String> response = new HashMap<>();
        response.put("message", "OTP has been sent to your email");
        response.put("email", email);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateOtp(@RequestBody OtpRequest request) {
        String email = request.getEmail();
        String otp = request.getOtp();

        boolean isValid = otpService.validateOtp(email, otp);
        Map<String, Object> response = new HashMap<>();

        if (isValid) {
            response.put("message", "OTP is valid. Email verified successfully!");
            response.put("emailVerified", true);
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid or expired OTP");
            response.put("emailVerified", false);
            return ResponseEntity.badRequest().body(response);
        }
    }
}
