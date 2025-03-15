import java.util.*;

public class q2 {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of a and b: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Enter the operation number which you want to perform as follows:");
        System.out.println("1-> addition +");
        System.out.println("2-> sbstraction -");
        System.out.println("3-> multiplication *");
        System.out.println("4-> division /");
        int choice = sc.nextInt();
        int result = 0;
        boolean validChoice = true;
        switch(choice) {
            case 1:
                System.out.println("Result is" + (a + b));
                case 2:
                System.out.println("Result is" + (a - b));
                break;
            case 3:
                System.out.println("Result is" + (a * b));
                break;
            case 4:
                if(b != 0) {
                    System.out.println("Result is" + (a / b));
                } else {
                    System.out.println("Division by zero is not allowed.");
                    validChoice = false;
                }
                break;
            default:
            System.out.println("Invalid choice");
        }
        if (validChoice) {
            System.out.println("The result is: " + result);
        }
    }
}