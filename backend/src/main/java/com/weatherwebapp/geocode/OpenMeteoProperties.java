package com.weatherwebapp.geocode;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weather.open-meteo")
public record OpenMeteoProperties(String geocodingUrl) {
}
