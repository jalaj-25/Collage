public class q10 {
    private String title;
    private String author;

    public q10(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
    }

    public static void main(String[] args) {
        q10 book = new q10("Java Programming", "John Doe");
        book.display();
    }
}
