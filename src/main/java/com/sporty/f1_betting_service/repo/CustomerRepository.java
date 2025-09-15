package com.sporty.f1_betting_service.repo;

import com.sporty.f1_betting_service.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
