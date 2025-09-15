package com.sporty.f1_betting_service.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateEventOutcomeRequestDto {

    @NotNull
    private Long eventId;

    @NotNull
    private String eventProvider;

    @NotNull
    private Long winningDriverId;
}
