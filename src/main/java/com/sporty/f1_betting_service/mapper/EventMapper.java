package com.sporty.f1_betting_service.mapper;

import com.sporty.f1_betting_service.api.dto.EventDto;
import com.sporty.f1_betting_service.service.eventprovider.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "eventId", source = "session.sessionKey")
    @Mapping(target = "sessionType", source = "session.sessionType")
    @Mapping(target = "eventName", source = "session.sessionName")
    @Mapping(target = "eventProvider", source = "eventProviderName")
    EventDto mapSessionToEventDto(Session session, String eventProviderName);
}
