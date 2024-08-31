package com.elearn.app.controllers;

import com.elearn.app.config.security.JwtUtil;
import com.elearn.app.dtos.CustomUserDetail;
import com.elearn.app.dtos.JwtResponse;
import com.elearn.app.dtos.LoginRequest;
import com.elearn.app.dtos.UserDto;
import com.elearn.app.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {


    private AuthenticationManager manager;

    private UserDetailsService userDetailsService;


    private JwtUtil util;

    private ModelMapper modelMapper;


    public AuthController(AuthenticationManager manager, UserDetailsService userDetailsService, JwtUtil util, ModelMapper modelMapper) {
        this.manager = manager;
        this.userDetailsService = userDetailsService;
        this.util = util;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody LoginRequest loginRequest) {


        try {

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authenticated = manager.authenticate(authentication);


        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Incorrect email or password !!");
        }

        //authenticated

        CustomUserDetail userDetails = (CustomUserDetail) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String token = util.generateToken(userDetails.getUsername());

        User user = userDetails.getUser();

        JwtResponse build = JwtResponse.builder()
                .token(token)
                .user(modelMapper.map(user, UserDto.class))
                .build();
        return ResponseEntity.ok(build);


    }
}
