package com.hasansahin.springsecuritydemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshTokenRequest {
    private String username;
    private String refreshToken;
}
