package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.OtpService;

public class OtpServiceImpl implements OtpService {

    @Override
    public String generateOtp(String email) {
        return "";
    }

    @Override
    public boolean validateOtp(String email, String otp) {
        return false;
    }
}
