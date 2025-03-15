import java.util.Scanner;

public class q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // System.out.println("Enter the size of string: ");
        // int n = sc.nextInt(); //string size
        
        System.out.println("Enter the  string: ");
        String str = sc.nextLine(); //string input
        
        int count1 = 0;

        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(c == 'a' || c == 'e'|| c == 'i'|| c == 'o'|| c == 'u' || c == 'A' || c == 'E'|| c == 'I'|| c == 'O'|| c == 'U') {
                count1++;
            }
        }
        System.out.println("Entered string has " + count1 + " vowels");
        sc.close();
    }
}
