package com.example.banking_system.Bank;
import java.util.HashMap;

import org.springframework.stereotype.Service;
@Service
public class BankService {
    private HashMap<String, BankAccount> account = new HashMap<>();

    public void createAccount(String accNo, String name){
        account.put(accNo, new BankAccount(accNo, name));
    }
    public BankAccount getAccount(String accNo){
        return account.get(accNo);
    }
}
