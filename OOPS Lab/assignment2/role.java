import java.util.Scanner;

public class role {  

    private String user_role;

    public role(String user_role) {
        this.user_role = user_role;
        if (user_role.equalsIgnoreCase("admin")) {
            System.out.println("You are authorized");
        } else {
            System.out.println("You are not authorized");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your role: ");
        String roleInput = sc.nextLine();
        new role(roleInput);
        sc.close();
    }
}
