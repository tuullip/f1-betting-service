package com.sporty.f1_betting_service.api.dto;

import com.sporty.f1_betting_service.api.dto.enums.SessionType;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class EventDto {

    private Integer eventId;
    private SessionType sessionType;
    private String eventName;
    private String eventProvider;
    private Integer year;
    private String countryCode;
    private String countryName;
    private Instant dateStart;
    private Instant dateEnd;
    private List<DriverMarketDto> driverMarkets;
}
