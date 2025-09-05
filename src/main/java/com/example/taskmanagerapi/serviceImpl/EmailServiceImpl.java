package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("obiorahgloria911@gmail.com");
        message.setTo(email);
        message.setSubject("Task manager registration successful!");
        message.setText("Registration to Task Manager successful! /n Your verification OTP is "+otp+ "/n This OTP expires in 10 minutes");
        message.setSentDate(new java.util.Date());
        mailSender.send(message);
    }
}
