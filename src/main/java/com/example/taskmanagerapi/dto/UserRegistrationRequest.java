package com.example.taskmanagerapi.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
    private String username;
    private String email;
    private String password;
    private String otpCode;
}
