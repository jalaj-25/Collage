public class q3 {
    private String name;;
    private int rollNo;

    q3(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public void display() {
        System.out.println("Name: " + name);
        System.out.println("RollNo: " + rollNo);
    }

    public static void main(String args[]) {
        q3 obj = new q3("Rahul", 10);
        obj.display();
    }
}
