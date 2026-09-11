package com.weatherwebapp.geocode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenMeteoGeocodingResponse(List<OpenMeteoPlace> results) {
}
