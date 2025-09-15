package com.sporty.f1_betting_service.mapper;

import com.sporty.f1_betting_service.api.dto.DriverMarketDto;
import com.sporty.f1_betting_service.service.eventprovider.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "driverId", source = "driverNumber")
    @Mapping(target = "oddsToWinEvent", expression = "java(new java.util.Random().nextInt(2, 4))")
    DriverMarketDto mapDriverToDriverMarketDto(Driver driver);
}
