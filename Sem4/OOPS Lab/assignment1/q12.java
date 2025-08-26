import java.util.Scanner;

public class q12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first string");
        String s1 = sc.nextLine();
        
        System.out.println("Enter second string");
        String s2 = sc.nextLine();

        if(s1.equals(s2)) {
            System.out.println("Using equals(): Strings are equal");
        } else {
            System.out.println("Using equals(): Strings are unequal");
        }

        if(s1 == s2) {
            System.out.println("Using == operator: Strings are equal");
        } else {
            System.out.println("Using == operator: Strings are unequal");
        }

        sc.close();
    }
}