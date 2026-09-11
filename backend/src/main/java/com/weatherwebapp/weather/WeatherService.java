package com.weatherwebapp.weather;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class WeatherService {

    private final OpenMeteoForecastClient client;

    public WeatherService(OpenMeteoForecastClient client) {
        this.client = client;
    }

    public WeatherResponse forecast(double latitude, double longitude) {
        OpenMeteoForecastResponse response = client.fetch(latitude, longitude);
        return new WeatherResponse(toCurrentWeather(response.current()), toDailyForecasts(response.daily()));
    }

    private CurrentWeather toCurrentWeather(OpenMeteoCurrent current) {
        return new CurrentWeather(
                current.temperature(),
                current.apparentTemperature(),
                current.relativeHumidity(),
                current.windSpeed(),
                current.isDay() == 1,
                current.weatherCode()
        );
    }

    private List<DailyForecast> toDailyForecasts(OpenMeteoDaily daily) {
        int days = daily.time().size();
        return IntStream.range(0, days)
                .mapToObj(i -> new DailyForecast(
                        daily.time().get(i),
                        daily.weatherCode().get(i),
                        daily.temperatureMax().get(i),
                        daily.temperatureMin().get(i),
                        daily.precipitationProbabilityMax().get(i)
                ))
                .toList();
    }
}
