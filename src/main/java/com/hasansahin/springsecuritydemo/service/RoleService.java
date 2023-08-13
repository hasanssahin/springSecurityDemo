package com.hasansahin.springsecuritydemo.service;

import com.hasansahin.springsecuritydemo.model.Role;
import com.hasansahin.springsecuritydemo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getByName(String name){
        return roleRepository.getByName(name);
    }
}
