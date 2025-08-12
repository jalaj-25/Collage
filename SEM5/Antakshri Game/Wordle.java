//dictionary is used for selecting words.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Wordle {

    private static int finalWordLength = 5;
    private static int maxAttempts = 6;
    private static List<String> wordList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadWord("dictionary.txt");
        String targetWord = wordList.get(new Random().nextInt(wordList.size()));
        int attempts = 0;
        display();

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            String guess = sc.nextLine().toLowerCase();

            if (guess.length() != finalWordLength) {
                System.out.println("Invalid guess. Please enter a " + finalWordLength + "-letter word.");
                continue;
            }
            attempts++;
            if(guess.equalsIgnoreCase(targetWord)) {
                System.out.println("Congratulations! You've guessed the word.");
                System.out.println("Attempt " + attempts + ": " + guess);
                break;
            } else {
                System.out.println("Attempt " + attempts + ": " + guess);
                System.out.println("Feedback: " + getFeedback(guess, targetWord));
            }

            if(attempts == maxAttempts) {
                System.out.println("Sorry, you've used all your attempts. The word was: " + targetWord);
            }
        }
    }

    private static String getFeedback(String guess, String targetWord) {
        StringBuilder feedback = new StringBuilder();
        for (int i = 0; i < finalWordLength; i++) {
            if (guess.charAt(i) == targetWord.charAt(i)) {
                feedback.append("O");
            } else if (targetWord.contains(String.valueOf(guess.charAt(i)))) {
                feedback.append("N");
            } else {
                feedback.append("*");
            }
        }
        return new String(feedback);
    }

    private static void loadWord(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String selectedWord;
            while((selectedWord = br.readLine()) != null) {
                selectedWord = selectedWord.trim().toLowerCase(); {
                    if(selectedWord.length() == finalWordLength)  {
                        wordList.add(selectedWord);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void display() {
        System.out.println("Welcome to Wordle!");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Rules: ");
        System.out.println("1. Each guess must be a " + finalWordLength + "-letter word.");
        System.out.println("2. You have " + maxAttempts + " attempts to guess the word.");
        System.out.println("3. After each guess, you will receive feedback on the letters.");
        System.out.println("--------------------------------------------------------------");
        System.out.println("4. Notations used: ");
        System.out.println("O: Correct letter in the correct position");
        System.out.println("N: Correct letter in the wrong position");
        System.out.println("*: Incorrect letter");
        System.out.println("--------------------------------------------------------------");
    }
}