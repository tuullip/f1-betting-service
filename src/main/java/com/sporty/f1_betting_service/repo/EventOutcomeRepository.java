package com.sporty.f1_betting_service.repo;

import com.sporty.f1_betting_service.domain.EventOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventOutcomeRepository extends JpaRepository<EventOutcome, Long> {

    Optional<EventOutcome> findByEventIdAndEventProvider(Long eventId, String eventProvider);
}