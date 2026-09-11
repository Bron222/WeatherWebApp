package com.weatherwebapp.geocode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeocodeServiceTest {

    @Mock
    private OpenMeteoGeocodingClient client;

    @InjectMocks
    private GeocodeService geocodeService;

    @Test
    void mapsOpenMeteoPlacesToGeocodeResults() {
        OpenMeteoPlace london = new OpenMeteoPlace(
                2643743L, "London", 51.50853, -0.12574, "Europe/London", "England", "United Kingdom");
        when(client.search("london")).thenReturn(List.of(london));

        GeocodeResponse response = geocodeService.search("london");

        assertThat(response.results()).hasSize(1);
        GeocodeResult result = response.results().get(0);
        assertThat(result.id()).isEqualTo(2643743L);
        assertThat(result.name()).isEqualTo("London");
        assertThat(result.region()).isEqualTo("England");
        assertThat(result.country()).isEqualTo("United Kingdom");
        assertThat(result.latitude()).isEqualTo(51.50853);
        assertThat(result.longitude()).isEqualTo(-0.12574);
        assertThat(result.timezone()).isEqualTo("Europe/London");
    }

    @Test
    void returnsEmptyResultsWhenClientFindsNothing() {
        when(client.search("asdfqwer")).thenReturn(List.of());

        GeocodeResponse response = geocodeService.search("asdfqwer");

        assertThat(response.results()).isEmpty();
    }
}
