package Ex3;

import java.io.File;

public class FileSearch {
    // The main method where the program starts execution
    public static void main(String[] args) {
        // Test the method with a real path and filename
        String path = "/Users/ahmedmunshi/eclipse-workspace1/Lab3recursion/src"; 
        String filename = "module-info.java";
        
        // Call the find method to search for the specified file in the given directory
        find(path, filename);
    }

    // Method to search for a file in a directory and its subdirectories
    public static void find(String path, String filename) {
        // Create a File object representing the directory
        File directory = new File(path);
        
        // Check if the directory exists
        if (!directory.exists()) {
            System.out.println("Directory does not exist: " + path);
            return;
        }
        
        // Check if the path represents a directory
        if (!directory.isDirectory()) {
            System.out.println("Path is not a directory: " + path);
            return;
        }
        
        // Get an array of files and directories in the directory
        File[] files = directory.listFiles();
        
        // Check if the array is not empty
        if (files != null) {
            // Iterate through each file and directory in the array
            for (File file : files) {
                // Check if it's a directory
                if (file.isDirectory()) {
                    // If it's a directory, recursively search inside
                    find(file.getAbsolutePath(), filename);
                } else {
                    // If it's a file, check if its name matches the desired filename
                    if (file.getName().equals(filename)) {
                        System.out.println("Found file: " + file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
