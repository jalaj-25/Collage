public class q7 {
    public static void main(String[] args) {
        Product product = new Product("Laptop");
        Product producta1 = new  Product("Mobile phone");
        Product product2 = new  Product("Tablet");
        product = null;
        product1 = null;
        System.gc(); 
        product2.display();
    }
}

class Product {
    String name;

    // Constructor
    Product(String name) {
        this.name = name;
        System.out.println("Product created: " + name);
    }

    void display(){
        System.out.println("Product is preserved.");
    }

    // Destructor (finalize method)
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Product destroyed: " + name);
    }
}