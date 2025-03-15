import java.util.Scanner;
public class q11 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string you want to get for concetination: ");
        String s1 = sc.nextLine();
        System.out.print("Enter second string you want to get for concetination: ");
        String s2 = sc.nextLine();

        String result = s1 + s2;
        System.out.println("Resultant string after concatenation is: ");
        System.out.println(result);

        sc.close();
    }
}
