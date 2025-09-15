package com.sporty.f1_betting_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventOutcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long eventId;

    private String eventProvider;

    private Long winningDriverId;

    private Instant timestamp;
}