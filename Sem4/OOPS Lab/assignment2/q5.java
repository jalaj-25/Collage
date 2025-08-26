public class q5 {
    private int radius;

    public q5() {
        this.radius = 1;
    }

    public q5(int radius) {
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public static void main(String args[]) {
        q5 defaultC = new q5();
        System.out.println("Area is: " + defaultC.area());
        
        q5 custom = new q5(5);
        System.out.println("Area is: " + custom.area());
    }
}
