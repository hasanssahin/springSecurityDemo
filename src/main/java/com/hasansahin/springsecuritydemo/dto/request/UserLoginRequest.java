package com.hasansahin.springsecuritydemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginRequest {
    private String username;
    private String password;
}
