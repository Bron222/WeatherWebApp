package com.weatherwebapp.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Lets the React dev server (a different origin) call /api/** from the browser.
 * Without this, the browser blocks the frontend from reading API responses.
 *
 * {@code @EnableConfigurationProperties} makes sure CorsProperties is loaded
 * wherever this class is, including the slimmed-down @WebMvcTest contexts.
 */
@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class WebConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public WebConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(corsProperties.allowedOrigins().toArray(String[]::new))
                // POST/PUT/DELETE are listed now for the Phase 3 favorites endpoints.
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
