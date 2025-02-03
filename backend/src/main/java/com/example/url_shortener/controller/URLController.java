package com.example.url_shortener.controller;

import com.example.url_shortener.model.ShortenedURL;
import com.example.url_shortener.service.URLShorteningService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class URLController {

    @Autowired
    private URLShorteningService urlService;

    // Shorten a URL
    @PostMapping("/shorten")
    public ShortenedURL shortenURL(@RequestBody String longURL) {
        return urlService.createShortenedURL(longURL);
    }

    // Redirect to the long URL
    @GetMapping("/{shortURL}")
    public ResponseEntity<Void> getLongURL(@PathVariable String shortURL) {
        String longURL = urlService.getLongURL(shortURL);
        if (longURL != null) {
            return ResponseEntity.status(HttpStatus.FOUND) // HTTP 302 redirect
                    .location(URI.create(longURL))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // TODO: Get all URLs for a user
    @GetMapping("/urls")
    public Iterable<ShortenedURL> getURLs() {
        return urlService.getURLs();
    }

    // Update a URL
    @PutMapping("/{shortURL}")
    public ResponseEntity<Void> updateURL(@PathVariable String shortURL, @RequestBody String longURL) {
        if (urlService.getLongURL(shortURL) != null) {
            urlService.updateURL(shortURL, longURL);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Delete a URL
    @DeleteMapping("/{shortURL}")
    public ResponseEntity<Void> deleteURL(@PathVariable String shortURL) {
        if (urlService.getLongURL(shortURL) != null) {
            urlService.deleteURL(shortURL);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}