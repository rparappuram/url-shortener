package com.url_shortener.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url_shortener.model.ShortenedURL;
import com.url_shortener.model.User;

public interface ShortenedURLRepository extends JpaRepository<ShortenedURL, Long> {
    ShortenedURL findByShortURL(String shortURL);

    List<ShortenedURL> findByUser(User user);
}