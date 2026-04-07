package com.aishwary.authsystem.service;

import com.aishwary.authsystem.dto.RegisterRequest;
import com.aishwary.authsystem.model.Role;
import com.aishwary.authsystem.model.User;
import com.aishwary.authsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        if (userRepo.existsByEmail((request.getEmail()))) return "Email already registered";
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(System.currentTimeMillis())
                .build();
        userRepo.save(user);
        return "User registered successfully";
    }
}
