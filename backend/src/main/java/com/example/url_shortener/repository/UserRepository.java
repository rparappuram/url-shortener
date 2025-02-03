package com.example.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.url_shortener.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
