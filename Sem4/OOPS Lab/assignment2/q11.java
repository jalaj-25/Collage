public class q11 {
    private String firstName;
    private String lastName;

    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;  
        this.lastName = lastName;    
    }

    public void display() {
        System.out.println("Full Name: " + firstName + " " + lastName);
    }

    public static void main(String[] args) {
        q11 person = new q11();
        person.setFullName("John", "Doe");
        person.display();
    }
}
