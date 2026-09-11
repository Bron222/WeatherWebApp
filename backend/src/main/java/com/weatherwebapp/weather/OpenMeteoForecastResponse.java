package com.weatherwebapp.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoForecastResponse(OpenMeteoCurrent current, OpenMeteoDaily daily) {
}
