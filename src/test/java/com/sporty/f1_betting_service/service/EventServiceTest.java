package com.sporty.f1_betting_service.service;

import com.sporty.f1_betting_service.api.dto.enums.SessionType;
import com.sporty.f1_betting_service.api.dto.request.SearchEventsRequestDto;
import com.sporty.f1_betting_service.service.eventprovider.Driver;
import com.sporty.f1_betting_service.service.eventprovider.EventProvider;
import com.sporty.f1_betting_service.service.eventprovider.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class EventServiceTest {

    @MockitoBean
    private EventProvider eventProvider;

    @Autowired
    private EventService eventService;

    @Test
    void searchEvents_shouldReturnMappedEventsWithDriverMarkets() {
        var searchEventsRequestDto = SearchEventsRequestDto.builder()
                .sessionType(SessionType.Race)
                .year(2025)
                .countryCode("GB")
                .build();

        var event = Session.builder().
                sessionKey(1).
                sessionType("Race").
                sessionName("British Grand Prix").
                dateStart(Instant.now()).
                dateEnd(Instant.now()).
                countryCode("GB").
                countryName("United Kingdom").
                year(2025)
                .build();

        var driver = Driver.builder()
                .driverNumber(1)
                .firstName("Max")
                .lastName("Verstappen")
                .build();

        when(eventProvider.searchEvents(
                searchEventsRequestDto.getSessionType().name(),
                searchEventsRequestDto.getYear(),
                searchEventsRequestDto.getCountryCode()))
                .thenReturn(List.of(event));
        when(eventProvider.searchEventDrivers(event.getSessionKey())).thenReturn(List.of(driver));

        var result = eventService.searchEvents(searchEventsRequestDto);
        assertEquals(1, result.size());
        assertEquals(result.get(0).getEventId(), event.getSessionKey());
        assertNotNull(result.get(0).getDriverMarkets());
        assertEquals(result.get(0).getDriverMarkets().get(0).getDriverId(), driver.getDriverNumber());
    }
}

