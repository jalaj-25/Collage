// Write	a	constructor	in	the	class	Employee	to	initialize	name,	employeeId,	and	salary.	Then,	create	an	employee	object	and	display	the	values.

public class q6 {
    private String name;
    private int employeeId;
    private int salary;

    q6() {
        this.name = "Jai";
        this.employeeId = 101;
        this.salary = 100000;
    }
    
    q6(String var, int e1, int s1) {
        this.name = var;
        this.employeeId = e1;
        this.salary = s1;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("EmployeeId: " + employeeId);
        System.out.println("Salary: " + salary);
    }

    public static void main(String[] args) {
        q6 var = new q6("Jai", 101, 100000);
        var.display();
    }
}
