package com.weatherwebapp.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoCurrent(
        @JsonProperty("temperature_2m") double temperature,
        @JsonProperty("relative_humidity_2m") int relativeHumidity,
        @JsonProperty("apparent_temperature") double apparentTemperature,
        @JsonProperty("is_day") int isDay,
        @JsonProperty("weather_code") int weatherCode,
        @JsonProperty("wind_speed_10m") double windSpeed
) {
}
