package com.example.url_shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.url_shortener.model.AuthenticationResponse;
import com.example.url_shortener.model.User;
import com.example.url_shortener.service.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/signup")
    public AuthenticationResponse signupUser(@RequestBody User user) {
        String token = authService.signupUser(user);
        return new AuthenticationResponse(token);
    }

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody User user) {
        String token = authService.loginUser(user);
        return new AuthenticationResponse(token);
    }
}
