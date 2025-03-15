// Create a	class Employee that has	an instance	variable name and	
// a local	variable name.Use the this keyword to distinguish between the	two.	
import java.util.Scanner; // Import Scanner

class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("Name: " + name);
    }
}
public class q12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name:");
        String name = sc.nextLine();
        Employee emp = new Employee(name);
        emp.display();
    }
}
