package com.example.banking_system.Bank;
// import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class BankService {
    // private HashMap<String, BankAccount> account = new HashMap<>();
@Autowired
private BankRepository bankRepository;

@Autowired
private UserRepository userRepository;
// Allow service method to accept the DTO
    public void createAccount(CreateAccountRequest request){
    
    BankAccount acc = new BankAccount();

    acc.setAccNo(request.getAccountNumber());
    acc.setBalance(request.getInitialDeposit());
    acc.setAccNo(request.getAccountNumber());

    // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    // String username = auth.getName();
    // System.out.println(username);

    // User user = userRepository.findByUsername(username)
    // .orElseThrow(()-> new ResourceNotFoundException("User not found"));

    // if(!acc.getUser().getUsername().equals(username)){
    //     throw new AccessDeniedException("Not your account");
    // }

    // acc.setUser(user);
    bankRepository.save(acc);

    }
    public BankAccount getAccount(String accNo){
        return bankRepository.findById(accNo).orElse(null);
    }
    public String deposit(String accNo, double amount){
            if(amount <= 0 ){
            throw new InvalidAmountException("Deposit must be grater than 0");
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        BankAccount acc = bankRepository.findById(username)
        .orElseThrow(()-> new ResourceNotFoundException("Account not found"));

        if(!acc.getUser().getUsername().equals(username)){
            throw new AccessDeniedException("Not your account");
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
