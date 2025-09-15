package com.sporty.f1_betting_service.service.eventprovider;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class Session {
    @JsonProperty("session_key")
    private Integer sessionKey;

    @JsonProperty("meeting_key")
    private Integer meetingKey;

    @JsonProperty("country_key")
    private Integer countryKey;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("session_name")
    private String sessionName;

    @JsonProperty("session_type")
    private String sessionType;

    @JsonProperty("date_start")
    private Instant dateStart;

    @JsonProperty("date_end")
    private Instant dateEnd;

    @JsonProperty("gmt_offset")
    private String gmtOffset;

    @JsonProperty("circuit_key")
    private Integer circuitKey;

    @JsonProperty("circuit_short_name")
    private String circuitShortName;

    @JsonProperty("year")
    private Integer year;
}
