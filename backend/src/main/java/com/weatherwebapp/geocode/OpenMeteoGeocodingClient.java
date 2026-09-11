package com.weatherwebapp.geocode;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class OpenMeteoGeocodingClient {

    private final RestClient restClient;
    private final OpenMeteoProperties properties;

    public OpenMeteoGeocodingClient(RestClient.Builder restClientBuilder, OpenMeteoProperties properties) {
        this.restClient = restClientBuilder.build();
        this.properties = properties;
    }

    public List<OpenMeteoPlace> search(String query) {
        URI uri = UriComponentsBuilder.fromUriString(properties.geocodingUrl())
                .queryParam("name", query)
                .queryParam("count", 10)
                .queryParam("language", "en")
                .queryParam("format", "json")
                .build()
                .toUri();

        OpenMeteoGeocodingResponse response = restClient.get()
                .uri(uri)
                .retrieve()
                .body(OpenMeteoGeocodingResponse.class);

        return Optional.ofNullable(response.results()).orElse(List.of());
    }
}