package com.example.banking_system.Bank;

import jakarta.persistence.Embeddable;

@Embeddable
public class Transaction {
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Long id;

private String type; //Deposit or Withdraw
private double amount;

// @ManyToOne
// private BankAccount bankAccount;
public Transaction(){}

public Transaction (String type, double amount){
    this.type = type;
    this.amount = amount;
}
// Add getters
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
}
