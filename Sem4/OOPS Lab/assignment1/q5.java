import java.util.Scanner;

public class q5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[5];
        int sum = 0;
        
        System.out.println("Enter five integers here: ");
        for(int i = 0; i < 5; i++) {
            System.out.println("Value at 1st position is: ");
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        System.out.println("Sum of the 5 values is: " + sum);
    }
}