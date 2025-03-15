public class q1 {
    private String title;
    private String Author;
    private double price;

    public q1(String title, String Author, double price) {
        this.title = title;
        this.Author = Author;
        this.price = price;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + Author);
        System.out.println("Price: " + price);
    }

    public static void main(String args[]) {
        q1 b1 = new q1("Harry Potter", "J.K. Rowling", 10.5);
        b1.display();
    }
}
