package com.example.banking_system.Bank;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class BankAccount {
    @Id
    private String accNo;
    private String name;
    private double balance;

    //Many transactions, one account 
    @OneToMany(mappedBy= "account", cascade = CascadeType.ALL) //Saving account saves transactions
    private List<Transaction> transactions = new ArrayList<>();

    public BankAccount(){} //Empty constructor for Spring + JPA input
    
    public BankAccount(String accNo, String name){
    this.accNo = accNo;
    this.name = name;
    this.transactions = new ArrayList<>();
}
    //Getters
    public String getName(){
        return name;
    }
    public String getAccNo(){
        return accNo;
    }
    //Setters
    public void setAccNo(String accNo){
        this.accNo = accNo;
    }
    public void setName(String name){
        this.name = name;
    }

    // Logic
    public double getBalance(){
        return balance;
    }
    public void deposit(double amount){
        if (amount>0){
            this.balance += amount;
            // transactions.add(new Transaction("DEPOSIT :", amount)); // Constructor call
            Transaction t = new Transaction("DEPOSIT", amount, this);
            transactions.add(t);
        }
    }
    public void withdraw(double amount){
        if(amount > 0 && balance >= amount){
            this.balance -= amount;
            Transaction t = new Transaction("WITHDRAW", amount, this);
            transactions.add(t);
        }
    }
    // Getter for Transactions
    public List<Transaction> getTransactions(){
      return transactions; // returns raw DB objects
    }

    // Unnecessary at this point
    // public void setBalance(double balance){
    //     this.balance = balance;
    // }
}


