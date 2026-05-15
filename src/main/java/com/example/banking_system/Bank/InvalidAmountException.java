package com.example.banking_system.Bank;

public class InvalidAmountException extends RuntimeException{
public InvalidAmountException(String message){
    super(message);
}
}
