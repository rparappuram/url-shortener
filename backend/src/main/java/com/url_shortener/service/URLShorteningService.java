package com.url_shortener.service;

import org.springframework.stereotype.Service;

import com.url_shortener.model.ShortenedURL;
import com.url_shortener.model.User;
import com.url_shortener.repository.ShortenedURLRepository;
import com.url_shortener.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class URLShorteningService {

    @Autowired
    private ShortenedURLRepository URLrepository;

    @Autowired
    private UserRepository userRepository;

    private User getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public String shortenURL(String longURL) {
        User user = getUser();
        String shortURL = UUID.randomUUID().toString().substring(0, 8);
        ShortenedURL shortenedURL = new ShortenedURL();
        shortenedURL.setLongURL(longURL);
        shortenedURL.setShortURL(shortURL);
        shortenedURL.setUser(user);
        URLrepository.save(shortenedURL);

        return shortURL;
    }

    public String getLongURL(String shortURL) {
        ShortenedURL shortenedURL = URLrepository.findByShortURL(shortURL);
        return (shortenedURL != null) ? shortenedURL.getLongURL() : null;
    }

    public List<ShortenedURL> getAllURLsForUser() {
        User user = getUser();
        return URLrepository.findByUser(user);
    }

    public void deleteURL(String shortURL) {
        ShortenedURL shortenedURL = URLrepository.findByShortURL(shortURL);
        if (shortenedURL != null) {
            URLrepository.delete(shortenedURL);
        }
    }
}