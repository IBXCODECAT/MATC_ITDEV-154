package com.schmitt.countingkeywords;

import java.util.*;
import java.io.*;
import java.nio.file.Paths;

/**
 * The CountingKeywords class provides methods to count keywords in a Java source file
 * and test the performance of a linked list.
 */
public class CountingKeywords {

    /**
     * Number of tests
     */
    private static final int N = 1000000;
    
    /**
     * Main method for counting keywords in a Java source file and testing linked list performance.
     *
     * @param args Command-line arguments (not used in this program).
     * @throws Exception If an error occurs while processing the source file.
     */
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Java source file: ");
        String filename = input.nextLine();
        File file = new File(filename);

        if (file.exists()) {
            System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
        } else {
            System.out.println("File " + filename + " does not exist in the current working directory (CWD)");
            System.out.println("CWD: " + Paths.get("").toAbsolutePath().toString());
        }
        
        // Create a linked list and test its performance
        Collection<Integer> list2 = new LinkedList<>();
        System.out.println("Member test time for linked list is " + getTestTime(list2) + " milliseconds");
        System.out.println("Remove element time for linked list is " + getRemoveTime(list2) + " milliseconds");
    }
    

    /**
     * Calculate the time taken to test if a number is in the collection.
     *
     * @param c The collection to test.
     * @return The time in milliseconds it took to perform the test.
     */
    public static long getTestTime(Collection<Integer> c) {
        long startTime = System.currentTimeMillis();

        // Test if a number is in the collection
        for (int i = 0; i < N; i++) {
            c.contains((int) (Math.random() * 2 * N));
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * Calculate the time taken to remove elements from the collection.
     *
     * @param c The collection to remove elements from.
     * @return The time in milliseconds it took to perform the removal.
     */
    public static long getRemoveTime(Collection<Integer> c) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            c.remove(i);
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * Count the number of keywords in a Java source file.
     *
     * @param file The Java source file to analyze.
     * @return The number of keywords found in the file.
     * @throws Exception If an error occurs while processing the file.
     */
    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false, and null
        String[] keywordString = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordString));
        int count = 0;
        Scanner input = new Scanner(file);

        while (input.hasNext()) {
            String word = input.next();
            if (keywordSet.contains(word)) { // Test if word is a keyword
                count++;
            }
        }

        return count;
    }
}
