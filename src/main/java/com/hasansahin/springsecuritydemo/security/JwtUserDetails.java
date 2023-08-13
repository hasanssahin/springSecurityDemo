package com.hasansahin.springsecuritydemo.security;

import com.hasansahin.springsecuritydemo.model.Role;
import com.hasansahin.springsecuritydemo.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class JwtUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            if ("user".equals(role.getName())) {
                authorityList.add(new SimpleGrantedAuthority("user"));
            } else if ("admin".equals(role.getName())) {
                authorityList.add(new SimpleGrantedAuthority("admin"));
            } else if ("seller".equals(role.getName())) {
                authorityList.add(new SimpleGrantedAuthority("seller"));
            }
        }
        return new JwtUserDetails(user.getUsername(), user.getPassword(), authorityList);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
