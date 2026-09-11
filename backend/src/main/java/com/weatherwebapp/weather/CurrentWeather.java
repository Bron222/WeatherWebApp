package com.weatherwebapp.weather;

public record CurrentWeather(
        double temperature,
        double apparentTemperature,
        int humidity,
        double windSpeed,
        boolean isDay,
        int weatherCode
) {
}
