package com.weatherwebapp.geocode;

import java.util.List;

public record GeocodeResponse(List<GeocodeResult> results) {
    
}
