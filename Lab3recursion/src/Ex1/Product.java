package Ex1;

public class Product {
    // The main method where the program starts execution
    public static void main(String[] args) {
        // Declare and initialize two integers
        int m = 10; // First number
        int n = 3;  // Second number
        
        // Call the multiply method to compute the product of m and n
        int result = multiply(m, n);
        
        // Display the result
        System.out.println("Product of " + m + " and " + n + " is: " + result);
    }

    // Method to calculate the product of two integers
    public static int multiply(int m, int n) {
        // If either m or n is 0, then the result is 0
        if (m == 0 || n == 0) {
            return 0;
        } else {
            // Call the linearProduct method to calculate the product recursively
            return linearProduct(m, n);
        }
    }

    // Method to calculate the product recursively
    public static int linearProduct(int m, int n) {
        // Base case: If m is 1, return n
        if (m == 1) {
            return n;
        } else {
            // Recursive case: return n added to the product of (m-1) and n
            return n + linearProduct(m - 1, n);
        }
    }
}
