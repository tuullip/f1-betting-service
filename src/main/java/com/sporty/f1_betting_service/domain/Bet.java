package com.sporty.f1_betting_service.domain;

import jakarta.persistence.*;
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
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private Long eventId;

    private String eventProvider;

    private Long bettingDriverId;

    private Integer bettingDriverWinningOdds;

    private Double amount;

    private String currency;

    @Builder.Default
    private BetStatus betStatus = BetStatus.PENDING;

    private Instant createTimestamp;

    private Instant processTimestamp;

    public enum BetStatus {
        PENDING,
        WON,
        LOST
    }
}

