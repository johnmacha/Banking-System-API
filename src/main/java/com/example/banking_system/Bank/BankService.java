package com.example.banking_system.Bank;
// import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BankService {
    // private HashMap<String, BankAccount> account = new HashMap<>();
@Autowired
private BankRepository bankRepository;

    public void createAccount(BankAccount acc){
        bankRepository.save(acc);
    }
    public BankAccount getAccount(String accNo){
        return bankRepository.findById(accNo).orElse(null);
    }
    public String deposit(String accNo, double amount){
        BankAccount acc = bankRepository.findById(accNo).orElse(null);

        if(acc == null){
            throw new ResourceNotFoundException("Account not Found!");
        }
        if(amount <= 0 ){
            throw new ResourceNotFoundException("Invalid amount!");
        }

        acc.deposit(amount);
        bankRepository.save(acc);

        return "Deposit successful!";
    }
    public String withdraw(String accNo, double amount){
        BankAccount acc = bankRepository.findById(accNo).orElse(null);
        if(acc == null){
            throw new ResourceNotFoundException("Account not Found!");
        }
        if(amount <= 0 ){
            throw new ResourceNotFoundException("Invalid amount!");
        }
        if(acc.getBalance() < amount){
            throw new ResourceNotFoundException("Insufficient funds!");
        }
        acc.withdraw(amount);
        bankRepository.save(acc);
        return "Withdrawal successful!";
    }
    public List<TransactionDTO> getTransactions(
        String accNo,
        String type,
        LocalDateTime start,
        LocalDateTime end,
        int page,
        int size
    ){
        BankAccount acc = bankRepository.findById(accNo).orElse(null);
        if (acc == null){
            throw new ResourceNotFoundException("Account not found!");
        }
        return acc.getTransactions()
        .stream()
        .filter(t -> type == null || t.getType().equalsIgnoreCase(type)) // e.i.c
        .filter(t -> start == null || !t.getTimestamp().isBefore(start))// After or equal to start
        .filter(t -> end == null || !t.getTimestamp().isAfter(end))// Before or equal to end
        //SORT (latest first)
        .sorted((t1,t2) -> t2.getTimestamp().compareTo(t1.getTimestamp()))
        //PAGINATION
        .skip((long)page*size)
        .limit(size)

        .map(
            t -> new TransactionDTO(
                t.getType(),
                t.getAmount(),
                t.getTimestamp()
            )
        ).collect(Collectors.toList());
    }
}
