package com.hasansahin.springsecuritydemo.repository;

import com.hasansahin.springsecuritydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String username);
}
