package com.weatherwebapp.geocode;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GeocodeService {

    private final OpenMeteoGeocodingClient client;

    public GeocodeService(OpenMeteoGeocodingClient client) {
        this.client = client;
    }

    public GeocodeResponse search(String query) {
        List<GeocodeResult> results = client.search(query).stream()
                .map(this::toGeocodeResult)
                .toList();

        return new GeocodeResponse(results);
    }

    private GeocodeResult toGeocodeResult(OpenMeteoPlace place) {
        return new GeocodeResult(
                place.id(),
                place.name(),
                place.admin1(),
                place.country(),
                place.latitude(),
                place.longitude(),
                place.timezone()
        );
    }
}