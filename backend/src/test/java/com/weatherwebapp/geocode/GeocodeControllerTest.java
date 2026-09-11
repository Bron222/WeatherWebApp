package com.weatherwebapp.geocode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClientException;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GeocodeController.class)
class GeocodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GeocodeService geocodeService;

    @Test
    void returnsResultsForAValidQuery() throws Exception {
        GeocodeResult london = new GeocodeResult(
                2643743L, "London", "England", "United Kingdom", 51.50853, -0.12574, "Europe/London");
        when(geocodeService.search("london")).thenReturn(new GeocodeResponse(List.of(london)));

        mockMvc.perform(get("/api/geocode").param("q", "london"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results[0].name").value("London"))
                .andExpect(jsonPath("$.results[0].country").value("United Kingdom"));
    }

    @Test
    void returnsBadRequestWhenQueryParamMissing() throws Exception {
        mockMvc.perform(get("/api/geocode"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Bad Request"));
    }

    @Test
    void returnsBadGatewayWhenUpstreamCallFails() throws Exception {
        when(geocodeService.search("london")).thenThrow(new RestClientException("boom"));

        mockMvc.perform(get("/api/geocode").param("q", "london"))
                .andExpect(status().isBadGateway())
                .andExpect(jsonPath("$.error").value("Bad Gateway"));
    }
}
