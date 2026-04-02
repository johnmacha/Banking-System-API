import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private List<String> transactions;

    public BankAccount(String accountNumber, String accountHolder){
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount){
        if (amount > 0){
        balance += amount; //balance = balance + amount;
        transactions.add("Deposited: " + amount);
        System.out.println("Deposit successful");
        }else{
            System.out.println("Invalid amount!");
        }
    }

    public void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Invalid amount");
        }
        else if (amount <= balance){
            balance -= amount; //balance = balance - amount;
            transactions.add("Withdrew :" + amount);
            System.out.println("Withdrawal successful");
        } else{
            System.out.println("Insufficient funds");
        }
    }

    public double getBalance(){
        return balance;
    }

    public void showTransactions(){
        System.out.println("Transaction History:");

        for (String t : transactions){
            System.out.println(t);
        }
    }
}