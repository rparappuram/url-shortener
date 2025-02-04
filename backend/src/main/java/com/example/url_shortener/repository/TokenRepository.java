package com.example.url_shortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.url_shortener.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByToken(String token);

    @Query("""
            SELECT t FROM Token t
            INNER JOIN User u ON t.user.id = u.id
            WHERE u.id = :userId and t.revoked = false
            """)
    List<Token> findAllByUserId(Long userId);
}
