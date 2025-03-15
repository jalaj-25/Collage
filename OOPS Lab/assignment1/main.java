import java.util.Scanner;
class main{
    public static void main(String[] args){
        System.out.println("Hello world");
        System.out.println("Input two numbers");
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int sum = a+b;
        System.out.println("The sum of the numbers are :" + sum);
        sc.close();
    }
}