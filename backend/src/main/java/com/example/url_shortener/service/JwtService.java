package com.example.url_shortener.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.url_shortener.model.Token;
import com.example.url_shortener.model.User;
import com.example.url_shortener.repository.TokenRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

@Component
public class JwtService {

    // TODO: Change to use environment variable
    public static final String SECRET = "KHbh89hebqSc65cRKFGDEc9hOn1GJn0SZauhze4DLaw="; // random Base64 string

    @Autowired
    private TokenRepository tokenRepository;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        String jwt = createToken(claims, user.getEmail());
        revokeAllTokensByUser(user);
        saveUserToken(jwt, user);
        return jwt;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValidToken(String token, UserDetails userDetails) {
        final String extractedEmail = extractEmail(token);
        final boolean isRevoked = tokenRepository.findByToken(token).isRevoked();
        return (extractedEmail.equals(userDetails.getUsername()) && !isTokenExpired(token) && !isRevoked);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private void saveUserToken(String jwt, User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setUser(user);
        tokenRepository.save(token);
    }

    private void revokeAllTokensByUser(User user) {
        List<Token> tokens = tokenRepository.findAllByUserId(user.getId());
        for (Token token : tokens) {
            token.setRevoked(true);
        }
        tokenRepository.saveAll(tokens);
    }
}
