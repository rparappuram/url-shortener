package com.example.url_shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.url_shortener.model.User;

@Service
public class AuthenticationService {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String signupUser(User user) {
        userDetailsService.signupUser(user);
        return jwtService.generateToken(user.getEmail());
    }

    public String loginUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getEmail());
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
