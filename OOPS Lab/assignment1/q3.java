// package java;
import java.util.Scanner;

public class q3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        double toFloat = num;
        
        double number = sc.nextDouble();
        int  toInt = (int)number;
        
        System.out.println("Value of num before conversion is: " + toFloat);
        System.out.println("Value of number before conversion is: " + number);
        
        System.out.println("Value of num after conversion is: " + num);
        System.out.println("Value of number after conversion is: " + toInt);
        
    }
}