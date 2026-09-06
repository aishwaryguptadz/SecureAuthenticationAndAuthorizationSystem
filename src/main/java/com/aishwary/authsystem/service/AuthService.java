package com.aishwary.authsystem.service;

import com.aishwary.authsystem.dto.AuthResponse;
import com.aishwary.authsystem.dto.LoginRequest;
import com.aishwary.authsystem.dto.RegisterRequest;
import com.aishwary.authsystem.model.RefreshToken;
import com.aishwary.authsystem.model.Role;
import com.aishwary.authsystem.model.User;
import com.aishwary.authsystem.repository.UserRepository;
import com.aishwary.authsystem.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public User register(RegisterRequest request) {
        if (userRepo.existsByEmail((request.getEmail()))) throw new RuntimeException("Email already exists");
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(System.currentTimeMillis())
                .build();
        return userRepo.save(user);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new RuntimeException("Invalid password");
        String accessToken = jwtService.generateToken(user.getEmail());
        RefreshToken refreshToken = refreshTokenService.createToken(user);
        return new AuthResponse(user.getId(), accessToken, refreshToken.getToken());
    }
}
