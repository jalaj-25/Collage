// Write a program that calculates the sum and average of the numbers in an array

import java.util.Scanner;

public class q14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of elements: ");
        int n = sc.nextInt();

        int[] numbers = new int[n];

        System.out.println("Enter " + " numbers: ");
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        double average = (double) sum / n;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);

        sc.close();
    }
}
