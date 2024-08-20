package com.elearn.app.services;

import com.elearn.app.dtos.CustomUserDetail;
import com.elearn.app.entities.User;
import com.elearn.app.repositories.UserRepo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new BadCredentialsException("User not found in database !!"));
        System.out.println("loading user form db" + user.getEmail());

        return new CustomUserDetail(user);
    }
}
