package com.weatherwebapp.weather;

public record DailyForecast(
        String date,
        int weatherCode,
        double tempMax,
        double tempMin,
        int precipitationChance
) {
}
