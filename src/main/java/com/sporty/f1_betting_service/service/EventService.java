package com.sporty.f1_betting_service.service;

import com.sporty.f1_betting_service.api.dto.EventDto;
import com.sporty.f1_betting_service.api.dto.request.CreateEventOutcomeRequestDto;
import com.sporty.f1_betting_service.api.dto.request.SearchEventsRequestDto;
import com.sporty.f1_betting_service.domain.EventOutcome;
import com.sporty.f1_betting_service.mapper.DriverMapper;
import com.sporty.f1_betting_service.mapper.EventMapper;
import com.sporty.f1_betting_service.repo.EventOutcomeRepository;
import com.sporty.f1_betting_service.service.eventprovider.EventProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventProvider eventProvider;
    private final EventMapper eventMapper;
    private final DriverMapper driverMapper;
    private final BetService betService;
    private final EventOutcomeRepository eventOutcomeRepository;

    public List<EventDto> searchEvents(SearchEventsRequestDto searchEventsRequestDto) {
        var events = eventProvider.searchEvents(
                Optional.ofNullable(searchEventsRequestDto.getSessionType())
                        .map(Enum::name)
                        .orElse(null),
                searchEventsRequestDto.getYear(),
                searchEventsRequestDto.getCountryCode()
        );

        var mappedEvents = events.stream()
                .map(event -> eventMapper.mapSessionToEventDto(event, eventProvider.getProviderName()))
                .toList();

        // OpenF1 API has a rate limit of 3 requests per second, so we limit the number of driver requests to 3 events
        for (int i = 0; i < Math.min(3, events.size()); i++) {
            var mappedEvent = mappedEvents.get(i);
            var drivers = eventProvider.searchEventDrivers(mappedEvent.getEventId())
                    .stream()
                    .map(driverMapper::mapDriverToDriverMarketDto)
                    .toList();
            mappedEvent.setDriverMarkets(drivers);
        }

        return mappedEvents;
    }


    @Transactional
    public Long createEventOutcome(CreateEventOutcomeRequestDto createEventOutcomeRequestDto) {
        eventOutcomeRepository.findByEventIdAndEventProvider(
                createEventOutcomeRequestDto.getEventId(),
                createEventOutcomeRequestDto.getEventProvider()
        ).ifPresent(eventOutcome -> {
            throw new RuntimeException(String.format("Event outcome already exists for eventId %d and provider %s",
                    createEventOutcomeRequestDto.getEventId(),
                    createEventOutcomeRequestDto.getEventProvider()));
        });

        var eventOutcome = EventOutcome.builder()
                .eventId(createEventOutcomeRequestDto.getEventId())
                .eventProvider(createEventOutcomeRequestDto.getEventProvider())
                .winningDriverId(createEventOutcomeRequestDto.getWinningDriverId())
                .timestamp(Instant.now())
                .build();

        betService.processEventOutcome(createEventOutcomeRequestDto.getEventId(),
                createEventOutcomeRequestDto.getEventProvider(),
                createEventOutcomeRequestDto.getWinningDriverId());

        return eventOutcomeRepository.save(eventOutcome).getId();
    }
}
