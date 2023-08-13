package com.hasansahin.springsecuritydemo.service;

import com.hasansahin.springsecuritydemo.dto.request.RefreshTokenRequest;
import com.hasansahin.springsecuritydemo.dto.request.UserCreateRequest;
import com.hasansahin.springsecuritydemo.dto.request.UserLoginRequest;
import com.hasansahin.springsecuritydemo.dto.response.UserAuthResponse;
import com.hasansahin.springsecuritydemo.model.RefreshToken;
import com.hasansahin.springsecuritydemo.model.User;
import com.hasansahin.springsecuritydemo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    public UserAuthResponse login(UserLoginRequest userLoginRequest) {
        String token = authenticateUser(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        User user = userService.getUserByUsername(userLoginRequest.getUsername());
        return UserAuthResponse.builder()
                .message("Login successful.")
                .username(userLoginRequest.getUsername())
                .accessToken(token)
                .refreshToken(refreshTokenService.createRefreshToken(user))
                .build();
    }

    public UserAuthResponse register(UserCreateRequest userCreateRequest) {
        if (userService.getUserByUsername(userCreateRequest.getUsername()) != null) {
            return UserAuthResponse.builder().message("Username already taken.").build();
        }
        User user = userService.save(userCreateRequest);
        String token = authenticateUser(userCreateRequest.getUsername(), userCreateRequest.getPassword());
        return UserAuthResponse.builder()
                .message("Registration successful, logged in.")
                .username(user.getUsername())
                .accessToken(token)
                .refreshToken(refreshTokenService.createRefreshToken(user))
                .build();
    }

    public UserAuthResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        RefreshToken refreshToken = refreshTokenService.getRefreshTokenByUser_Username(refreshTokenRequest.getUsername());
        if (refreshToken.getRefreshToken().equals(refreshTokenRequest.getRefreshToken()) && !refreshTokenService.isRefreshExpired(refreshToken)) {
            User user = refreshToken.getUser();
            String accessToken = jwtTokenProvider.generateJwtTokenByUsername(refreshTokenRequest.getUsername());
            return UserAuthResponse.builder()
                    .message("Access token updated.")
                    .username(user.getUsername())
                    .accessToken("Bearer " + accessToken)
                    .build();
        } else {
            return UserAuthResponse.builder()
                    .message("Refresh token could not be verified.")
                    .build();
        }
    }

    private String authenticateUser(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateJwtTokenByAuthentication(authentication);
        return "Bearer " + token;
    }

}
