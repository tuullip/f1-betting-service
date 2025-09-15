package com.sporty.f1_betting_service.api;

import com.sporty.f1_betting_service.api.dto.BetDto;
import com.sporty.f1_betting_service.api.dto.request.CreateBetRequestDto;
import com.sporty.f1_betting_service.service.BetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bets")
@RequiredArgsConstructor
public class BetController {

    private final BetService betService;

    @PostMapping
    public BetDto placeBet(@RequestBody @Valid CreateBetRequestDto createBetRequestDto) {
        return betService.createBet(createBetRequestDto);
    }
}
