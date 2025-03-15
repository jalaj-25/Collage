public class q2 {
    private String model;
    private String make;
    private int year;

    public q2(String model, String make, int year) {
        this.model = model;
        this.make = make;
        this.year = year;
    }

    public void display() {
        System.out.println("Model: " + model);
        System.out.println("Manufactring Company: " + make);
        System.out.println("Manufactring year: " + year);
    }

    public static void main(String args[]) {
        q2 obj = new q2("Toyota", "Toyota", 2015);
        obj.display();
    }
}
