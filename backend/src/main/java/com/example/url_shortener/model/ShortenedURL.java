package com.example.url_shortener.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class ShortenedURL {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "long_url", nullable = false)
    private String longURL;

    @Column(name = "short_url", nullable = false, unique = true)
    private String shortURL;

    private LocalDateTime createdAt;

    public ShortenedURL() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLongURL() {
        return longURL;
    }
    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }
    public String getShortURL() {
        return shortURL;
    }
    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShortenedURL{");
        for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
            try {
                sb.append(field.getName()).append("='").append(field.get(this)).append("', ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
