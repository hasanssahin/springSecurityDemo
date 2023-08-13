package com.hasansahin.springsecuritydemo.config;

import com.hasansahin.springsecuritydemo.model.Role;
import com.hasansahin.springsecuritydemo.repository.RoleRepository;
import com.hasansahin.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role role=new Role("admin");
        Role role1=new Role("user");
        Role role2=new Role("seller");

        roleRepository.saveAll(Arrays.asList(role,role1,role2));
    }
}
