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
        balance += amount; //balance = balance + amount;
        transactions.add("Deposited: " + amount);
    }

    public void withdraw(double amount){
        if (amount <= balance){
            balance -= amount; //balance = balance - amount;
            transactions.add("Withdrew :" + amount);
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