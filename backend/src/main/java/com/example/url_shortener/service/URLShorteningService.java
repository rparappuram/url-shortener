package com.example.url_shortener.service;

import org.springframework.stereotype.Service;
import com.example.url_shortener.model.ShortenedURL;
import com.example.url_shortener.repository.ShortenedURLRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class URLShorteningService {

    @Autowired
    private ShortenedURLRepository repository;

    public ShortenedURL createShortenedURL(String longURL) {
        String shortURL = UUID.randomUUID().toString().substring(0, 8);
        while (repository.findByShortURL(shortURL) != null) { // Ensure uniqueness
            shortURL = UUID.randomUUID().toString().substring(0, 8);
        }
        ShortenedURL shortenedURL = new ShortenedURL();
        shortenedURL.setLongURL(longURL);
        shortenedURL.setShortURL(shortURL);
        return repository.save(shortenedURL);
    }

    public String getLongURL(String shortURL) {
        ShortenedURL shortenedURL = repository.findByShortURL(shortURL);
        return (shortenedURL != null) ? shortenedURL.getLongURL() : null;
    }

    public Iterable<ShortenedURL> getURLs() {
        return repository.findAll();
    }

    public void deleteURL(String shortURL) {
        repository.deleteByShortURL(shortURL);
    }

    public void updateURL(String shortURL, String longURL) {
        ShortenedURL shortenedURL = repository.findByShortURL(shortURL);
        if (shortenedURL != null) {
            shortenedURL.setLongURL(longURL);
            repository.save(shortenedURL);
        }
    }
}