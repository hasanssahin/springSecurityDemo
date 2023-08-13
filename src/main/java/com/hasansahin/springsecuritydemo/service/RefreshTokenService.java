package com.hasansahin.springsecuritydemo.service;

import com.hasansahin.springsecuritydemo.model.RefreshToken;
import com.hasansahin.springsecuritydemo.model.User;
import com.hasansahin.springsecuritydemo.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${refresh.token.expires.in}")
    private Long expireMilliSeconds;

    public RefreshToken getRefreshTokenByUser_Username(String username) {
        return refreshTokenRepository.getRefreshTokenByUser_Username(username);
    }

    public String createRefreshToken(User user) {
        RefreshToken refreshToken = refreshTokenRepository.getRefreshTokenByUser_Username(user.getUsername());
        if (refreshToken == null) {
            refreshToken = new RefreshToken();
        }
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Date.from(Instant.now().plusMillis(expireMilliSeconds)));
        return refreshTokenRepository.save(refreshToken).getRefreshToken();
    }

    public boolean isRefreshExpired(RefreshToken refreshToken) {
        return refreshToken.getExpiryDate().before(new Date());
    }

}
