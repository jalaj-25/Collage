// Create a class Account with	private	properties accountNumber and balance.	
// Write public getter	and	setter	methods	to access and modify these properties.

import java.util.Scanner;

class Acc {
    private int accNumber;
    private double balance;

    public Acc(int accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public int getAcc() {
        return accNumber;
    }

    public void setAcc(int accNumber) {
        this.accNumber = accNumber;
    }

    public double getB() {
        return balance;
    }

    public void setB(double balance) {
        if(balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    public void display() {
        System.out.println("Account details:");
        System.out.println("Account number: " + accNumber);
        System.out.println("Account balance: " + balance);
    }
}

public class q13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter account number: ");
        int accNumber = sc.nextInt();
        
        System.out.println("Enter initial balance: ");
        double balance = sc.nextDouble();

        Acc acc = new Acc(accNumber, balance);
        acc.display();

        System.out.println("Enter the new balance: ");
        double newBalance = sc.nextDouble();

        acc.setB(newBalance);
        sc.close();
    }
}
