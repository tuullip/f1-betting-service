package com.sporty.f1_betting_service.service;

import com.sporty.f1_betting_service.api.dto.BetDto;
import com.sporty.f1_betting_service.api.dto.request.CreateBetRequestDto;
import com.sporty.f1_betting_service.domain.Bet;
import com.sporty.f1_betting_service.mapper.BetMapper;
import com.sporty.f1_betting_service.repo.BetRepository;
import com.sporty.f1_betting_service.repo.CustomerRepository;
import com.sporty.f1_betting_service.repo.CustomerWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BetService {

    private final BetRepository betRepository;
    private final CustomerRepository customerRepository;
    private final CustomerWalletRepository customerWalletRepository;
    private final BetMapper betMapper;
    private final BetProcessor betProcessor;

    @Transactional
    public BetDto createBet(CreateBetRequestDto createBetRequestDto) {
        var user = customerRepository.findById(createBetRequestDto.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        var userWallet = customerWalletRepository.findByCustomerAndCurrency(user, createBetRequestDto.getCurrency())
                .orElseThrow(() -> new IllegalArgumentException("Customer wallet not found"));

        if (userWallet.getBalance() < createBetRequestDto.getBettingAmount()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        var bet = Bet.builder()
                .eventId(createBetRequestDto.getEventId())
                .eventProvider(createBetRequestDto.getEventProvider())
                .bettingDriverId(createBetRequestDto.getBettingDriverId())
                .bettingDriverWinningOdds(new Random().nextInt(2, 4)) // Probably has to be fetched externally when processing the bet
                .amount(createBetRequestDto.getBettingAmount())
                .currency(createBetRequestDto.getCurrency())
                .customer(user)
                .createTimestamp(Instant.now())
                .build();

        userWallet.setBalance(userWallet.getBalance() - createBetRequestDto.getBettingAmount());

        return betMapper.mapBetToBetDto(betRepository.save(bet));
    }

    public void processEventOutcome(Long eventId, String eventProvider, Long winningDriverId) {
        var bets = betRepository.findByEventIdAndEventProviderAndBetStatus(eventId, eventProvider, Bet.BetStatus.PENDING);
        for (Bet bet : bets) {
            betProcessor.processBet(bet, winningDriverId);
        }
    }
}