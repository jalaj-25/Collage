import java.util.Scanner;

public class q8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array you want: ");
        int n = sc.nextInt(); //get the size of array
        int a[] = new int[n]; //define the array
        
        for(int i = 0; i < n; i++) { //take input of array
            a[i] = sc.nextInt(); //get the elements in the array
        }

        System.out.println("Enter the element you want to search: ");
        int m = sc.nextInt(); //element to be searched
        //traverse and check if element exist
        for(int i = 0;i < n; i++) {
            if(a[i] == m) {
                System.out.println("Element found at index " + i);
            } else {
                continue;
            }
        }

    }
}
