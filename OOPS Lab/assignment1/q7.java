import java.util.Scanner;

public class q7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows (n): ");
        int n = sc.nextInt();
        System.out.print("Enter the number of columns (m): ");
        int m = sc.nextInt();
        int a[][] = new int[n][m];
        int b[][] = new int[n][m];
        int sum[][] = new int[n][m];
        System.out.println("Enter elements of 1st matrix:");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }
        
        System.out.println("Enter elements of 2nd matrix:");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                b[i][j] = sc.nextInt();
            }
        }
        
        System.out.println("Sum of matrix: ");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sum[i][j] = a[i][j] + b[i][j];
            }
        }
        
        System.out.println("Sum of the two matrices:");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }  
        
    }
}
