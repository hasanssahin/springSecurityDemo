package com.hasansahin.springsecuritydemo.repository;

import com.hasansahin.springsecuritydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role getByName(String name);
}
