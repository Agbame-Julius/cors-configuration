package com.example.cors_project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws SecurityException{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> {
                    CorsConfigurationSource source = request -> {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(
                                List.of("example.com", "example.org", "http://localhost:8080"));
                        config.setAllowedMethods(
                                List.of("GET", "PUT", "POST", "DELETE")
                        );
                        config.addAllowedHeader("*");
                      return config;
                    };
                    c.configurationSource(source);
                })
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .build();
    }
}
