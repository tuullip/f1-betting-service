package com.sporty.f1_betting_service.repo;

import com.sporty.f1_betting_service.domain.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    List<Bet> findByEventIdAndEventProviderAndBetStatus(Long eventId, String eventProvider, Bet.BetStatus betStatus);
}
