import java.util.Scanner;

public class camparison {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        // int choice;
        System.out.println("num1 > num2: " + (a > b));
        System.out.println("num1 < num2: " + (a < b));
        System.out.println("num1 == num2: " + (a == b));
        System.out.println("num1 != num2: " + (a != b));
        System.out.println("num1 >= num2: " + (a >= b));
        System.out.println("num1 <= num2: " + (a <= b));
    }
}