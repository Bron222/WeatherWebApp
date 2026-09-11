package com.weatherwebapp.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @Test
    void returnsForecastForValidCoordinates() throws Exception {
        CurrentWeather current = new CurrentWeather(20.0, 19.6, 53, 3.6, false, 1);
        DailyForecast day = new DailyForecast("2026-09-11", 53, 21.7, 16.1, 76);
        when(weatherService.forecast(51.5, -0.13)).thenReturn(new WeatherResponse(current, List.of(day)));

        mockMvc.perform(get("/api/weather").param("lat", "51.5").param("lon", "-0.13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.current.temperature").value(20.0))
                .andExpect(jsonPath("$.daily[0].date").value("2026-09-11"));
    }

    @Test
    void returnsBadRequestWhenCoordinatesMissing() throws Exception {
        mockMvc.perform(get("/api/weather"))
                .andExpect(status().isBadRequest());
    }
}
