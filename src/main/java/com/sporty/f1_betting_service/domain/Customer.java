package com.sporty.f1_betting_service.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerWallet> wallets;
}

