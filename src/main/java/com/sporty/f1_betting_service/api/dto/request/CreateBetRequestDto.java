package com.sporty.f1_betting_service.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBetRequestDto {

    @NotNull
    private Long customerId;

    @NotNull
    private Long eventId;

    @NotNull
    private String eventProvider;

    @NotNull
    private Long bettingDriverId;

    @NotNull
    private Double bettingAmount;

    @NotNull
    private String currency;
}
