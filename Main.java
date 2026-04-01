import java.util.Scanner;

public class Main {
    // Add 'helper-methods'
    public static void handleDeposit(Scanner sc, BankAccount currentAccount){
        if (currentAccount != null){
            System.out.println("Enter amount:");
            double amount = sc.nextDouble();
            currentAccount.deposit(amount);
        }
        else{
            System.out.println("Please login first!");
        }
    }
    public static void handleWithdraw(Scanner sc, BankAccount currentAccount){
        if(currentAccount != null){
        System.out.println("Enter amount to withdraw");
        double amount = sc.nextDouble();
        currentAccount.withdraw(amount);
        }
        else{
            System.out.println("Please login first!");
        }
        }
        public static void handleTransactions(BankAccount curreAccount){
            if(curreAccount !=  null){
            curreAccount.showTransactions();
            System.out.println("Thank you come again next time! ");
            }else{
                System.out.println("Please login first!");
            }
        }
    public static void handleBalance(BankAccount currenAccount){
        if(currenAccount != null){
            System.out.println("Your Balance is: "+ currenAccount.getBalance());
            System.out.println("Thank you!");
        }
        else{
            System.out.println("Please login first!");
        }
    }
    public static void main (String args []){
        // Create an account using 'Object Instantiation',where 'bank' is a 'reference variable'
        Bank bank = new Bank();
        Scanner sc = new Scanner(System.in); //To allow user input
        BankAccount currentAccount = null; //For creating account that stays logged in

        System.out.println("Select your option...");
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Check Balance");
        System.out.println("6. View Transactions");
        System.out.println("7. Exit");
     
    while (true) { 
        int choice = sc.nextInt(); // Waits for user to type a number
        System.out.println("\n==== BANK MENU ====");

        if (choice==1) {
            System.out.println("Enter account number: ");
            String accNo = sc.next();

            System.out.println("Enter name: ");
            String name = sc.next();

            bank.createAccount(accNo, name);
            System.out.println("Account created!");
        }

        else if(choice == 2){
            System.out.println("Enter account number:");
            String accNo = sc.next();
            BankAccount acc = bank.getAccount(accNo);

            if(acc != null){
                currentAccount = acc; //set logged-in account
                System.out.println("Login successful!");
            }
            else{
                System.out.println("Account not found!");
            }
        }
        else if(choice == 3){
           handleDeposit(sc, currentAccount);
        }
        else if (choice == 4){
           handleWithdraw(sc, currentAccount);
        }

        else if (choice == 5){
           handleBalance(currentAccount);
        }
        else if(choice == 6){
            handleTransactions(currentAccount);
        }
        else if(choice == 7){
            System.out.println("Good bye!");
            break;
        }
        }
        }
       
    }
    


