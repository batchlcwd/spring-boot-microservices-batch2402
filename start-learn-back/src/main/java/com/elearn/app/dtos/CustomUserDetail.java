package com.elearn.app.dtos;

import com.elearn.app.entities.Role;
import com.elearn.app.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetail implements UserDetails {

    private User user;


    public User getUser() {
        return user;
    }

    public CustomUserDetail(User user) {
        this.user = user;
    }


    // roles / authorization
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // create and return user roles/authority
        //GrantedAuthority
        return user.getRoles().
                stream().
                map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
