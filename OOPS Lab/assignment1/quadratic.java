import java.util.*;

public class Quadratic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the values of a, b, and c:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        double d = (b * b) - (4 * a * c);

        if (d > 0) {
            double root1 = (-b + Math.sqrt(d)) / (2 * a);
            double root2 = (-b - Math.sqrt(d)) / (2 * a);
            System.out.println("The roots are real and distinct:");
            System.out.println("Root 1: " + root1);
            System.out.println("Root 2: " + root2);
        } else if (d == 0) {
            double root = -b / (2.0 * a);
            System.out.println("The roots are real and equal:");
            System.out.println("Root: " + root);
        } else{
            double realPart = -b / (2.0 * a);
            double imaginaryPart = Math.sqrt(-d) / (2 * a);
            System.out.println("The roots are complex:");
            System.out.println("Root 1: " + realPart + " + " + imaginaryPart + "i");
            System.out.println("Root 2: " + realPart + " - " + imaginaryPart + "i");
        }

        sc.close();
    }
}