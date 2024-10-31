package com.meme.generator.memegeneratorapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Autoriser tous les endpoints
                .allowedOrigins("http://localhost:4200") // Autoriser ton frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autoriser les méthodes nécessaires
                .allowCredentials(true);
    }
}
