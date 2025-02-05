package com.example.url_shortener.repository;

import com.example.url_shortener.model.ShortenedURL;
import com.example.url_shortener.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedURLRepository extends JpaRepository<ShortenedURL, Long> {
    ShortenedURL findByShortURL(String shortURL);

    List<ShortenedURL> findByUser(User user);
}