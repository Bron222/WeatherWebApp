package com.weatherwebapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.open-meteo")
public record OpenMeteoProperties(String geocodingUrl, String forecastUrl) {
}
