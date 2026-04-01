import java.util.Scanner;

public class Main {
    public static void main (String args []){
        // Create an account using 'Object Instantiation',where 'ac' is a 'reference variable'
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
           
            if (currentAccount != null){
                System.out.println("Enter amount : ");
                double amount = sc.nextDouble();
                currentAccount.deposit(amount);
                System.out.println("Deposit successful");
            } else{
                System.out.println("Account not found!");
            }
            
        }
        else if (choice == 4){
            if(currentAccount != null){
            System.out.println("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            currentAccount.withdraw(amount);
            }
            else{
                System.out.println("Account not found!");
            }
        }

        else if (choice == 5){
            if(currentAccount != null){
            System.out.println("Balance :"+currentAccount.getBalance());
            System.out.println("Thank you!");
            }
            else{
                System.out.println("Account not found!");
            }
        }
        else if(choice == 6){
            if(currentAccount != null){
                currentAccount.showTransactions();
                System.out.println("Thank you come again next time!");
            }
            else{
                System.out.println("Please login first!");
            }
        }
        else if(choice == 7){
            System.out.println("Good bye!");
            break;
        }

        }
        }
    }


