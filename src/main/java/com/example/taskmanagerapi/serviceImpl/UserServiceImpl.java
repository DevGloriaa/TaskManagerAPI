package com.example.taskmanagerapi.serviceImpl;

import com.example.taskmanagerapi.dto.LoginDto;
import com.example.taskmanagerapi.dto.UserRegistrationRequest;
import com.example.taskmanagerapi.model.User;
import com.example.taskmanagerapi.repository.UserRepository;
import com.example.taskmanagerapi.service.EmailService;
import com.example.taskmanagerapi.service.OtpService;
import com.example.taskmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final OtpService otpService;
    private final EmailService emailService;

    // Temporary storage for pending users until they verify
    private final Map<String, String> pendingUsernames = new HashMap<>();
    private final Map<String, String> pendingPasswords = new HashMap<>();

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           OtpService otpService,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    @Override
    public String registration(UserRegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use!");
        }

        pendingUsernames.put(request.getEmail(), request.getUsername());
        pendingPasswords.put(request.getEmail(), request.getPassword());

        String otp = otpService.generateOtp(request.getEmail());
        emailService.sendOtpEmail(request.getEmail(), otp);

        return "OTP sent. Verify your email to complete registration.";
    }

    @Override
    public boolean verifyOtp(String email, String otpCode) {
        if (!otpService.validateOtp(email, otpCode)) {
            throw new RuntimeException("OTP is invalid or expired!");
        }

        String username = pendingUsernames.get(email);
        String rawPassword = pendingPasswords.get(email);

        if (username == null || rawPassword == null) {
            throw new RuntimeException("No pending registration found for this email!");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setIsverified(true);

        userRepository.save(user);

        pendingUsernames.remove(email);
        pendingPasswords.remove(email);

        return true;
    }

    @Override
    public boolean resendOtp(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email is already verified or registered!");
        }
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
        return true;
    }


    @Override
    public boolean login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("User does not exist"));

        if (!user.isIsverified()) {
            throw new RuntimeException("Your email has not been verified!");
        }

        return passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
    }
}
