package com.sporty.f1_betting_service.api;

import com.sporty.f1_betting_service.api.dto.EventDto;
import com.sporty.f1_betting_service.api.dto.request.CreateEventOutcomeRequestDto;
import com.sporty.f1_betting_service.api.dto.request.SearchEventsRequestDto;
import com.sporty.f1_betting_service.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventDto> getEvents(SearchEventsRequestDto searchEventsRequestDto) {
        return eventService.searchEvents(searchEventsRequestDto);
    }

    @PostMapping("/outcome")
    public Long createEventOutcome(@RequestBody @Valid CreateEventOutcomeRequestDto createEventOutcomeRequestDto) {
        return eventService.createEventOutcome(createEventOutcomeRequestDto);
    }
}
