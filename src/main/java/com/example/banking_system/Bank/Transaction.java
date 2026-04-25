package com.example.banking_system.Bank;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//Make Transaction a Real Entity
@Entity // Marks class as database table
public class Transaction {
@Id // Marks the primary key - unique identifier
@GeneratedValue(strategy = GenerationType.IDENTITY) //Lets persistence provider automatically assign values to PK fields
private Long id;
private String type; //Deposit or Withdraw
private double amount;
private LocalDateTime timestamp;

@ManyToOne // Each transaction belongs to one account 
@JoinColumn(name="account_name") // Marks the column  as a join column for an entity association or element collection
@JsonIgnore
private BankAccount account;

public Transaction(){}

public Transaction (String type, double amount, BankAccount account){
    this.type = type;
    this.amount = amount;
    this.account = account;
    this.timestamp = LocalDateTime.now();
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
