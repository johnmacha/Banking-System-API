package com.example.banking_system.Bank;

import java.time.LocalDateTime;
// This controls exactly what the client sees
public class TransactionDTO {
    private String type;
    private double amount;
    private LocalDateTime timestamp;

    public TransactionDTO(String type, double amount, LocalDateTime timestamp){
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    // Add getters
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }

}
