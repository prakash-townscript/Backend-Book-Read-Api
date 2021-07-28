package com.townscript.goodreadsapi.service;

import com.townscript.goodreadsapi.Repository.RefreshTokenRepository;
import com.townscript.goodreadsapi.exception.SpringCustomException;
import com.townscript.goodreadsapi.model.RefreshToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringCustomException("Invalid refresh Token"));
    }

    public Object deleteRefreshToken(String token) {
        return refreshTokenRepository.deleteByToken(token);
    }
}