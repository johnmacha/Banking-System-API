package com.example.banking_system.Bank;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<BankAccount, Long>{
    Optional<BankAccount> findByAccNo(String accNo);
}
