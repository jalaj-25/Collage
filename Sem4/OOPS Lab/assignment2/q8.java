public class q8 {
    static class Product {
        private String name;

        public Product(String name) {
            this.name = name;
            System.out.println("Product created: " + name);
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Product with name " + name + " is being garbage collected");
        }
    }

    public static void main(String[] args) {
        Product p1 = new Product("Laptop");
        Product p2 = new Product("Phone");

        p1 = null;
        p2 = null;

        System.gc();

        try {
            Thread.sleep(1000); // Give time for GC
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
    