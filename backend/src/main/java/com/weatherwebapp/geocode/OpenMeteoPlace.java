package com.weatherwebapp.geocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoPlace(
        long id,
        String name,
        double latitude,
        double longitude,
        String timezone,
        String admin1,
        String country
) {
}