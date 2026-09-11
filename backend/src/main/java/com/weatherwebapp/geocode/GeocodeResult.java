package com.weatherwebapp.geocode;

public record GeocodeResult(long id,
                            String name,
                            String region,
                            String country,
                            double latitude,
                            double longitude,
                            String timezone) {

}
