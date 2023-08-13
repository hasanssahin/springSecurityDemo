package com.hasansahin.springsecuritydemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequest {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
}
