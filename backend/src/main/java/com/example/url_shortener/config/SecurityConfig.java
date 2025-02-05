package com.example.url_shortener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.url_shortener.filter.JwtAuthenticationFilter;
import com.example.url_shortener.service.UserDetailsServiceImp;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private CustomLogoutHandler customLogoutHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll() // Allow all requests to /api/auth/**
                        .requestMatchers("/api/{shortURL}").permitAll() // Allow all requests to /api/{shortURL}
                        .anyRequest().authenticated() // Protect all other endpoints
                ).userDetailsService(userDetailsService()) // Custom user details service
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No sessions
                )
                .authenticationProvider(authenticationProvider()) // Custom authentication provider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                .exceptionHandling(
                        e -> e.accessDeniedHandler((request, response, accessDeniedException) -> response
                                .sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage()))
                                .authenticationEntryPoint((request, response, authException) -> response
                                        .sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage()))) // TODO:
                                                                                                                      // For
                                                                                                                      // some
                                                                                                                      // reason,
                                                                                                                      // this
                                                                                                                      // runs
                                                                                                                      // on
                                                                                                                      // internal
                                                                                                                      // server
                                                                                                                      // error
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout") // Custom logout URL
                        .addLogoutHandler(customLogoutHandler) // Custom logout handler
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext() // Clear
                                                                                                            // security
                                                                                                            // context
                        ))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encoding
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}