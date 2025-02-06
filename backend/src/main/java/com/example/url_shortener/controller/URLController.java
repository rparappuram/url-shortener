package com.example.url_shortener.controller;

import com.example.url_shortener.model.ShortenedURL;
import com.example.url_shortener.service.URLShorteningService;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class URLController {

    @Autowired
    private URLShorteningService urlService;

    @PostMapping("/url")
    public ResponseEntity<Object> shortenURL(@RequestBody Map<String, String> request) {
        String longURL = request.get("longURL");
        String shortURL = urlService.shortenURL(longURL);
        return ResponseEntity.ok(Map.of("shortURL", shortURL));
    }

    @DeleteMapping("/url")
    public ResponseEntity<Void> deleteURL(@RequestBody Map<String, String> request) {
        String shortURL = request.get("shortURL");
        urlService.deleteURL(shortURL);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{shortURL}")
    public ResponseEntity<Void> redirectToLongURL(@PathVariable String shortURL) {
        String longURL = urlService.getLongURL(shortURL);
        if (longURL != null) {
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(longURL)).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/urls")
    public ResponseEntity<Iterable<ShortenedURL>> getAllURLsForUser() {
        Iterable<ShortenedURL> urls = urlService.getAllURLsForUser();
        return ResponseEntity.ok(urls);
    }
}