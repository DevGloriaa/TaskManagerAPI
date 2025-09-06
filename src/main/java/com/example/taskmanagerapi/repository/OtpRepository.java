package com.example.taskmanagerapi.repository;

import com.example.taskmanagerapi.model.Otp;
import com.example.taskmanagerapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<Otp, String> {
  Optional<Otp> findByEmailAndOtp(String email, String otp);
}
