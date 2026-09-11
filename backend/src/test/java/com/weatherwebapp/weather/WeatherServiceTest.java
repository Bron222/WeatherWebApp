package com.weatherwebapp.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private OpenMeteoForecastClient client;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    void mapsCurrentConditionsAndZipsDailyArraysIntoForecastList() {
        OpenMeteoCurrent current = new OpenMeteoCurrent(20.0, 53, 19.6, 0, 1, 3.6);
        OpenMeteoDaily daily = new OpenMeteoDaily(
                List.of("2026-09-11", "2026-09-12"),
                List.of(53, 3),
                List.of(21.7, 23.5),
                List.of(16.1, 14.3),
                List.of(76, 12)
        );
        when(client.fetch(51.5, -0.13)).thenReturn(new OpenMeteoForecastResponse(current, daily));

        WeatherResponse response = weatherService.forecast(51.5, -0.13);

        assertThat(response.current().temperature()).isEqualTo(20.0);
        assertThat(response.current().humidity()).isEqualTo(53);
        assertThat(response.current().isDay()).isFalse();

        assertThat(response.daily()).hasSize(2);
        assertThat(response.daily().get(0).date()).isEqualTo("2026-09-11");
        assertThat(response.daily().get(0).tempMax()).isEqualTo(21.7);
        assertThat(response.daily().get(1).date()).isEqualTo("2026-09-12");
        assertThat(response.daily().get(1).precipitationChance()).isEqualTo(12);
    }
}
