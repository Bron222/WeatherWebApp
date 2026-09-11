package com.weatherwebapp.weather;

import com.weatherwebapp.config.OpenMeteoProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class OpenMeteoForecastClient {

    private final RestClient restClient;
    private final OpenMeteoProperties properties;

    public OpenMeteoForecastClient(RestClient.Builder restClientBuilder, OpenMeteoProperties properties) {
        this.restClient = restClientBuilder.build();
        this.properties = properties;
    }

    public OpenMeteoForecastResponse fetch(double latitude, double longitude) {
        URI uri = UriComponentsBuilder.fromUriString(properties.forecastUrl())
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("current", "temperature_2m,relative_humidity_2m,apparent_temperature,is_day,weather_code,wind_speed_10m")
                .queryParam("daily", "weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max")
                .queryParam("timezone", "auto")
                .queryParam("forecast_days", 7)
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(OpenMeteoForecastResponse.class);
    }
}
