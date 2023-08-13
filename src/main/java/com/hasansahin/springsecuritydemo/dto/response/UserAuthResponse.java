package com.hasansahin.springsecuritydemo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserAuthResponse {
    private String message;
    private String username;
    private String accessToken;
    private String refreshToken;
}
