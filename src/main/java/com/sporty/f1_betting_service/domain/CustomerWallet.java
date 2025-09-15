package com.sporty.f1_betting_service.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CustomerWallet {

    @Id
    private Long id;

    private Double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String currency;
}