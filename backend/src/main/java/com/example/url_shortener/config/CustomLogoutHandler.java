package com.example.url_shortener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.example.url_shortener.model.Token;
import com.example.url_shortener.repository.TokenRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class CustomLogoutHandler implements LogoutHandler {
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        String token = authHeader.substring("Bearer ".length());
        Token storedToken = tokenRepository.findByToken(token);
        if (storedToken != null) {
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }

}
