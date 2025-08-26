import java.util.Scanner;

public class q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int row = col = 2;
        int mat1[][] = new int[2][2];
        int mat2[][] = new int[2][2];
        int sum[][] = new int[2][2];
        //matrix 1
        System.out.println("Enter 1st matrix: ");
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                mat1[i][j] = sc.nextInt();
            }
        }
        //matrix 2
        System.out.println("Enter 2nd matrix: ");
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                mat2[i][j] = sc.nextInt();
            }
        }
        //matrix Sum
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                sum[i][j] = mat1[i][j] + mat2[i][j];
            }
        }
        //output
        System.out.println("resultant matrix is: ");
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 2; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }
}