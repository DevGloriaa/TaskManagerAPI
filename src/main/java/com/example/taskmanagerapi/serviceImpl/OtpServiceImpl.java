package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.model.Otp;
import com.example.taskmanagerapi.repository.OtpRepository;
import com.example.taskmanagerapi.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private OtpRepository otpRepository;


    @Override
    public String generateOtp(String email) {
        String otp = String.format("%06d", new Random().nextInt(999999));

        otpRepository.deleteByEmail(email);


        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(10));

        otpRepository.save(otpEntity);

        return otp;
    }

    @Override
    public boolean validateOtp(String email, String code) {
        Optional<Otp> otpOptional = otpRepository.findByEmail(email);

        if (otpOptional.isEmpty()) return false;

        Otp otp = otpOptional.get();

        if (otp.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpRepository.deleteByEmail(email);
            return false;
        }

        boolean isValid = otp.getOtp().equals(code);

        if (isValid) {
            otpRepository.deleteByEmail(email);
        }

        return isValid;
    }
}
