package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String email, String otp) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("obiorahgloria911@gmail.com");
            helper.setTo(email);
            helper.setSubject("Task Manager - Email Verification");

            String htmlContent = """
                    <div style="font-family: Arial, sans-serif; color: #333;">
                        <h2>✅ Registration Successful!</h2>
                        <p>Welcome to <b>Task Manager</b> 🎉</p>
                        <p>Your OTP code is:</p>
                        <h3 style="color: #2e86de;">%s</h3>
                        <p>This OTP will expire in <b>10 minutes</b>.</p>
                        <hr>
                        <small>If you did not request this, please ignore this email.</small>
                    </div>
                    """.formatted(otp);

            helper.setText(htmlContent, true);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }
}
