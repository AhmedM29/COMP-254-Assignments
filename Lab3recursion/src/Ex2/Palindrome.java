package Ex2;

import java.util.Scanner;

public class Palindrome {
    // The main method where the program starts execution
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter a string
        System.out.print("Enter a string to check if it's a palindrome: ");
        
        // Read the input string
        String input = scanner.nextLine();
        
        // Close the Scanner object to prevent resource leak
        scanner.close();

        // Check if the input string is a palindrome and display the result
        if (isPalindrome(input)) {
            System.out.println("'" + input + "' is a palindrome.");
        } else {
            System.out.println("'" + input + "' is not a palindrome.");
        }
    }

    // Method to check if a string is a palindrome
    public static boolean isPalindrome(String s) {
        // Base case: If the string has 0 or 1 character, it's a palindrome
        if (s.length() <= 1) {
            return true;
        } else {
            // Check if the first and last characters are equal
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                // Recur with the substring excluding the first and last characters
                return isPalindrome(s.substring(1, s.length() - 1));
            } else {
                // If the first and last characters are not equal, it's not a palindrome
                return false;
            }
        }
    }
}
