package com.hasansahin.springsecuritydemo.dto.converter;

import com.hasansahin.springsecuritydemo.dto.request.UserCreateRequest;
import com.hasansahin.springsecuritydemo.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User convertUserCreateRequesttoUser(UserCreateRequest userCreateRequest) {
        return new User(
                userCreateRequest.getName(),
                userCreateRequest.getSurname(),
                userCreateRequest.getEmail(),
                userCreateRequest.getUsername(),
                userCreateRequest.getPassword()
        );
    }
}
