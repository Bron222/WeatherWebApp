package com.weatherwebapp.geocode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeocodeController {

    private final GeocodeService geocodeService;

    public GeocodeController(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @GetMapping("/api/geocode")
    public GeocodeResponse search(@RequestParam String q) {
        return geocodeService.search(q);
    }
}