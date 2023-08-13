package com.hasansahin.springsecuritydemo.service;

import com.hasansahin.springsecuritydemo.dto.converter.UserConverter;
import com.hasansahin.springsecuritydemo.dto.request.UserCreateRequest;
import com.hasansahin.springsecuritydemo.exception.GenericException;
import com.hasansahin.springsecuritydemo.model.User;
import com.hasansahin.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    public User save(UserCreateRequest userCreateRequest,String roleName) {
        User user = userConverter.convertUserCreateRequesttoUser(userCreateRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.getByName(roleName));
        return userRepository.save(user);
    }
}
