package com.example.banking_system.Bank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/create")
    public String createAccount(@RequestBody BankAccount account){
        bankService.createAccount(account.getAccNo(), account.getName());
        return "Account created successfully!";
    }
    @PostMapping("/deposit")
    public String deposit(@RequestParam String accNo, @RequestParam double amount){
        return bankService.deposit(accNo, amount);
    }
    @PostMapping("withdraw")
    public String withdraw(@RequestParam String accNo, @RequestParam double amount){
        return bankService.withdraw(accNo, amount);
    }
    @GetMapping("/balance")
    public String getBalance(@RequestParam String accNo){
        BankAccount acc = bankService.getAccount(accNo);
        if(acc==null){
            return "Account not found";
        }
        return "Balance: "+acc.getBalance();
    }
    @GetMapping("/transaction")
    public List<Transaction> transactions(@RequestParam String accNo){
        BankAccount acc = bankService.getAccount(accNo);
        if (acc == null){
            return null;
        }
       return acc.getTransactions();
         
    }    
    @GetMapping("/transactions")
    public List<TransactionDTO> getTransactions(@RequestParam String accNo){
        return bankService.getTransactions(accNo);
    }
}
