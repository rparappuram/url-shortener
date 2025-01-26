package com.example.url_shortener.repository;

import com.example.url_shortener.model.ShortenedURL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedURLRepository extends JpaRepository<ShortenedURL, Long> {
    ShortenedURL findByShortURL(String shortURL);
    void deleteByShortURL(String shortURL);
}