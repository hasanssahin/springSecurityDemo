package com.hasansahin.springsecuritydemo.repository;

import com.hasansahin.springsecuritydemo.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken getRefreshTokenByUser_Username(String username);
}
