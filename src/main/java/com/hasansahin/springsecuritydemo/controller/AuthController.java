package com.hasansahin.springsecuritydemo.controller;

import com.hasansahin.springsecuritydemo.dto.request.RefreshTokenRequest;
import com.hasansahin.springsecuritydemo.dto.request.UserCreateRequest;
import com.hasansahin.springsecuritydemo.dto.request.UserLoginRequest;
import com.hasansahin.springsecuritydemo.dto.response.UserAuthResponse;
import com.hasansahin.springsecuritydemo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(authenticationService.login(userLoginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserAuthResponse> register(@RequestBody UserCreateRequest userCreateRequest, @RequestParam String roleName) {
        return ResponseEntity.ok(authenticationService.register(userCreateRequest,roleName));
    }

    @PostMapping("/refresh")
    public ResponseEntity<UserAuthResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refresh(refreshTokenRequest));
    }
}
