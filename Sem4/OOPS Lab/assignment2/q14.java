import java.util.Scanner;
class Bank {
    private double balance;

    public Bank(double initialBalance) { 
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            checkBalance(); 
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }
}
public class q14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter initial balance: ");
        double initialBalance = sc.nextDouble();
        
        Bank myAccount = new Bank(initialBalance);
        System.out.println("Enter amount to deposit: ");
        double depositAmount = sc.nextDouble();

        myAccount.deposit(depositAmount); // Corrected argument type

        sc.close();
    }
}
