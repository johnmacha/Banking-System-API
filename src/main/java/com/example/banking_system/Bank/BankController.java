package com.example.banking_system.Bank;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<String>> createAccount(
        @Valid @RequestBody BankAccount acc
    ){
        bankService.createAccount(acc);
        
        return ResponseEntity.ok(
            new APIResponse<>("success", "Account created", null)
        );
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
    @GetMapping("/{accNo}/transactions")
    public ResponseEntity<APIResponse<List<TransactionDTO>>> getTransactions(
        @PathVariable String accNo,
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String start,
        @RequestParam(required = false) String end,
        @RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="5") int size
        ){
           LocalDateTime startDate = (start != null) ? LocalDateTime.parse(start) : null; //Ternary operator
           LocalDateTime endDate = (end != null) ? LocalDateTime.parse(end) : null;
            
           List<TransactionDTO> transactions = 
           bankService.getTransactions(accNo, type, startDate, endDate, page, size);// Object reference assignment from method call
        
            // if(transactions == null){
            //     return ResponseEntity.badRequest().body(
            //         new APIResponse<>("error","Acount not found", null)
            //     );
            // } The exception is being handled globally
            return ResponseEntity.ok(
                new APIResponse<>("success", "Transactions retrieved",transactions)
            );
        }
}
