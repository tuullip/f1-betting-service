package com.sporty.f1_betting_service.api.dto;

import lombok.Data;

@Data
public class DriverMarketDto {

    private String driverId;
    private String firstName;
    private String lastName;
    private Integer oddsToWinEvent;
}
