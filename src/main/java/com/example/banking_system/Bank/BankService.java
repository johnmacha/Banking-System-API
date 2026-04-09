package com.example.banking_system.Bank;
// import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class BankService {
    // private HashMap<String, BankAccount> account = new HashMap<>();
@Autowired
private BankRepository bankRepository;

    public void createAccount(String accNo, String name){
        BankAccount acc = new BankAccount(accNo, name);
        bankRepository.save(acc);
    }
    public BankAccount getAccount(String accNo){
        return bankRepository.findById(accNo).orElse(null);
    }
    public String deposit(String accNo, double amount){
        BankAccount acc = bankRepository.findById(accNo).orElse(null);

        if(acc == null){
            return "Account not found!";
        }
        if(amount <= 0 ){
            return "Invalid amount!";
        }

        acc.deposit(amount);
        bankRepository.save(acc);

        return "Deposit successful!";
    }
    public String withdraw(String accNo, double amount){
        BankAccount acc = bankRepository.findById(accNo).orElse(null);
        if(acc == null){
            return "Account not found";
        }
        if(amount <= 0 ){
            return "Invalid amount!";
        }
        if(acc.getBalance() < amount){
            return "Insufficient funds";
        }
        acc.withdraw(amount);
        bankRepository.save(acc);
        return "Withdrawal successful!";
    }
}
