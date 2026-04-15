package com.aishwary.authsystem.service;

import com.aishwary.authsystem.model.RefreshToken;
import com.aishwary.authsystem.model.User;
import com.aishwary.authsystem.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createToken(User user) {

        RefreshToken token = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)
                .build();

        return refreshTokenRepository.save(token);
    }
}