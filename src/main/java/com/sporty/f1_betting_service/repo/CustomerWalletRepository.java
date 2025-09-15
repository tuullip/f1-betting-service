package com.sporty.f1_betting_service.repo;

import com.sporty.f1_betting_service.domain.Customer;
import com.sporty.f1_betting_service.domain.CustomerWallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerWalletRepository extends CrudRepository<CustomerWallet, Long> {

    Optional<CustomerWallet> findByCustomerAndCurrency(Customer customer, String currency);
}
