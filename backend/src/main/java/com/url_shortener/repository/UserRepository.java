package com.url_shortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url_shortener.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
