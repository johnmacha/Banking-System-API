// A separate file to test the use of try-catch
import java.util.Scanner;

public class Menu {
    // Add try-catch for Menu input and Amount input
    public static void main(String [] args){
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter number: ");
    try{
        int num = sc.nextInt();
        System.out.println("Your value is :"+ num); 
       
    }
    catch(java.util.InputMismatchException e){
        System.out.println("Invalid input!");
        sc.nextLine(); // Clear bad input
    }   
    }
    
}
