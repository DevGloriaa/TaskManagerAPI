package com.example.taskmanagerapi.serviceImpl;


import com.example.taskmanagerapi.model.PasswordResetToken;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.PasswordResetTokenRepository;
import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    private JavaMailSender mailSender;

    public String generateResetToken(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("No account found with that email.");
        }

        tokenRepository.deleteByEmail(email);

        String token = UUID.randomUUID().toString();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(15);

        PasswordResetToken resetToken = new PasswordResetToken(token, email, expiry);
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:5500/reset-password.html?token=" + token;
        System.out.println("🔗 Password reset link: " + resetLink);

        if (mailSender != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("Click the link below to reset your password:\n\n" + resetLink);
            mailSender.send(message);
        }

        return "Password reset link sent to " + email;
    }

    public String resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token."));

        if (resetToken.isExpired()) {
            throw new RuntimeException("Token has expired.");
        }

        User user = userRepository.findByEmail(resetToken.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found."));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);

        return "Password reset successfully.";
    }
}

