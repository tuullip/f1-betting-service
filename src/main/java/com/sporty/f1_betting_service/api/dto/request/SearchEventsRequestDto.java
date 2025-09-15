package com.sporty.f1_betting_service.api.dto.request;

import com.sporty.f1_betting_service.api.dto.enums.SessionType;
import lombok.*;

@Data
@Builder
public class SearchEventsRequestDto {

    private SessionType sessionType;
    private Integer year;
    private String countryCode;
}
