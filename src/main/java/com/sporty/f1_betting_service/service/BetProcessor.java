package com.sporty.f1_betting_service.service;

import com.sporty.f1_betting_service.domain.Bet;
import com.sporty.f1_betting_service.repo.BetRepository;
import com.sporty.f1_betting_service.repo.CustomerWalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BetProcessor {

    private final BetRepository betRepository;
    private final CustomerWalletRepository customerWalletRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processBet(Bet bet, Long winningDriverId) {
        if (bet.getBettingDriverId().equals(winningDriverId)) {
            bet.setBetStatus(Bet.BetStatus.WON);
            bet.setProcessTimestamp(Instant.now());
            var customerWallet = customerWalletRepository.findByCustomerAndCurrency(bet.getCustomer(), bet.getCurrency())
                    .orElseThrow(() -> new IllegalArgumentException("Customer wallet not found"));
            var winnings = bet.getAmount() * bet.getBettingDriverWinningOdds();
            customerWallet.setBalance(customerWallet.getBalance() + winnings);
        } else {
            bet.setBetStatus(Bet.BetStatus.LOST);
        }

        betRepository.save(bet);
    }
}
