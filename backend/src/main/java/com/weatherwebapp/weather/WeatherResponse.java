package com.weatherwebapp.weather;

import java.util.List;

public record WeatherResponse(CurrentWeather current, List<DailyForecast> daily) {
}
