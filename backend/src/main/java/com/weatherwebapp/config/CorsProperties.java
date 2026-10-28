package com.weatherwebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Which browser origins (scheme + host + port) may call the API.
 * Bound from {@code weather.cors.allowed-origins} in application.yaml.
 */
@ConfigurationProperties(prefix = "weather.cors")
public record CorsProperties(List<String> allowedOrigins) {
}
