package com.sporty.f1_betting_service.service.eventprovider;

import java.util.List;

public interface EventProvider {

    List<Session> searchEvents(String eventType, Integer year, String country_code);

    List<Driver> searchEventDrivers(Integer eventId);

    String getProviderName();
}
