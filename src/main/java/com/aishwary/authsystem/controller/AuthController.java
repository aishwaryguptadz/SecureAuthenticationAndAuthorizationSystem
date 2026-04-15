package com.aishwary.authsystem.controller;

import com.aishwary.authsystem.dto.*;
import com.aishwary.authsystem.model.RefreshToken;
import com.aishwary.authsystem.model.User;
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
    public ApiResponse<User> register(@Valid @RequestBody RegisterRequest request) {
        User savedUser = authService.register(request);
        return new ApiResponse<>("success", "User registered successfully", savedUser);
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return new ApiResponse<>("success", "Login successful", response);
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthResponse> refresh(@RequestBody RefreshRequest request) {
        RefreshToken token = refreshTokenRepo.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        if (token.getExpiryDate() < System.currentTimeMillis())
            throw new RuntimeException("Refresh token expired");
        User user = token.getUser();
        String newAccessToken = jwtService.generateToken(user.getEmail());
        AuthResponse response = new AuthResponse(
                user.getId(),
                newAccessToken,
                request.getRefreshToken()
        );
        return new ApiResponse<>(
                "success",
                "Access token refreshed successfully",
                response
        );
    }
}
