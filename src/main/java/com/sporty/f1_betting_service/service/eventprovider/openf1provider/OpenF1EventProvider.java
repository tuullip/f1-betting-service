package com.sporty.f1_betting_service.service.eventprovider.openf1provider;

import com.sporty.f1_betting_service.service.eventprovider.Driver;
import com.sporty.f1_betting_service.service.eventprovider.Session;
import com.sporty.f1_betting_service.service.eventprovider.EventProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OpenF1EventProvider implements EventProvider {

    private final RestTemplate restTemplate;
    private final static String SESSIONS_PATH = "/sessions";
    private final static String DRIVERS_PATH = "/drivers";
    @Value("${open-f1.base-url}")
    private String BASE_URL;

    @Override
    public List<Session> searchEvents(String eventType, Integer year, String country_code) {
        var uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .path(SESSIONS_PATH)
                .queryParamIfPresent("session_type", Optional.ofNullable(eventType))
                .queryParamIfPresent("year", Optional.ofNullable(year))
                .queryParamIfPresent("country_code", Optional.ofNullable(country_code))
                .build()
                .encode()
                .toUri()
                .toString();
        var result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Session>>() {
        });
        return result.getBody();
    }

    @Override
    public List<Driver> searchEventDrivers(Integer eventId) {
        var uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .path(DRIVERS_PATH)
                .queryParam("session_key", Optional.of(eventId))
                .build()
                .encode()
                .toUri();
        var result = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<Driver>>() {
        });
        return result.getBody();
    }

    @Override
    public String getProviderName() {
        return "open_f1";
    }
}
