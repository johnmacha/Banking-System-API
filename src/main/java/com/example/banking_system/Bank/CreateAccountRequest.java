package com.example.banking_system.Bank;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateAccountRequest {
@NotBlank
private String accountName;

@NotBlank
private String accountNumber;

@NotNull
@PositiveOrZero
private Double initialDeposit;

//Add getters
public String getAccountNumber(){
    return accountNumber;
}
public String getAccountName(){
    return accountName;
}
public Double getInitialDeposit(){
    return initialDeposit;
}

//Add setters
public void setAccNo(String accountNumber){
    this.accountNumber = accountNumber;
} 
public void setAccountName(String accountName){
    this.accountName = accountName;
}
public void setInitialDeposit(double initialDeposit){
this.initialDeposit = initialDeposit;
}

}
