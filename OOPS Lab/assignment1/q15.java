// Write a program that uses nested loops to print a 
// pattern of asterisks (e.g., a triangle or pyramid)

import java.util.Scanner;

public class q15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows ofpyramid: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n - i; j++) {
                System.out.println(" ");
            }

            for(int k = 1; k <= (2 * i - 1); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
        sc.close();
    }
}
