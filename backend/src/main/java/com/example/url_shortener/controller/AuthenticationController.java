package com.example.url_shortener.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.url_shortener.model.User;
import com.example.url_shortener.service.AuthenticationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome, this endpoint is not secure";
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody User user) {
        String token = authService.signupUser(user);
        String headerValue = "Bearer " + token;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.CREATED.value());
        body.put("message", "User created successfully");
        body.put("expirationTime", LocalDateTime.now().plusDays(1)); // TODO: Change to use environment variable

        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.AUTHORIZATION, headerValue)
                .body(body);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        String token = authService.loginUser(user);
        String headerValue = "Bearer " + token;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.OK.value());
        body.put("message", "User logged in successfully");
        body.put("expirationTime", LocalDateTime.now().plusDays(1)); // TODO: Change to use environment variable

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, headerValue)
                .body(body);
    }
}
