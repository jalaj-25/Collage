public class q4 {
    private int height;
    private int width;

    public q4(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void display() {
        System.out.println("Height: " + height);
        System.out.println("Width: " + width);
    }
    
    public int area() {
        return height * width;
    }
    
    public static void main(String args[]) {
        q4 obj = new q4(5, 3);
        obj.display();
        System.out.println("Area: " + obj.area());
    }
}
