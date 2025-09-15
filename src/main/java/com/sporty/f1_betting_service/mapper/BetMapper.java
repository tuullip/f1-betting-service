package com.sporty.f1_betting_service.mapper;

import com.sporty.f1_betting_service.api.dto.BetDto;
import com.sporty.f1_betting_service.domain.Bet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BetMapper {

    BetDto mapBetToBetDto(Bet bet);
}
