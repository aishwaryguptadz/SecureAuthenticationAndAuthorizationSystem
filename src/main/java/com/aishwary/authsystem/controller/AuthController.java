package com.aishwary.authsystem.controller;

import com.aishwary.authsystem.dto.*;
import com.aishwary.authsystem.model.RefreshToken;
import com.aishwary.authsystem.repository.RefreshTokenRepository;
import com.aishwary.authsystem.security.JwtService;
import com.aishwary.authsystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenRepository refreshTokenRepo;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return new ApiResponse<>("success", "User registered successfully", null);
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody LoginRequest request) {
        String token = String.valueOf(authService.login(request));
        return new ApiResponse<>("success", "Login successful", token);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshRequest request) {
        RefreshToken token = refreshTokenRepo.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException(("Invalid refresh token")));
        if (token.getExpiryDate() < System.currentTimeMillis()) throw new RuntimeException("Refresh token expired");
        String newAccessToken = jwtService.generateToken(token.getUser().getEmail());
        return new AuthResponse(newAccessToken, request.getRefreshToken());
    }
}
