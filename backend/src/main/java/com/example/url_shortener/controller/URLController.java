package com.example.url_shortener.controller;

import com.example.url_shortener.model.ShortenedURL;
import com.example.url_shortener.service.URLShorteningService;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class URLController {

    @Autowired
    private URLShorteningService urlService;


    @PostMapping("/shorten")
    public ShortenedURL shortenURL(@RequestBody String longURL) {
        return urlService.createShortenedURL(longURL);
    }

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
}