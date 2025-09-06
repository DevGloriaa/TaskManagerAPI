package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.OtpService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public String generateOtp(String email) {
       String otp = String.valueOf(10000 + new Random().nextInt(90000));
       otpStorage.put(email, otp);
       return otp;
    }

    @Override
    public boolean validateOtp(String email, String otp) {
        if (otpStorage.containsKey(email) && otpStorage.get(email).equals(otp)){
            otpStorage.remove(email);
            return true;
        }
        return false;
    }
}
