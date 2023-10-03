package com.schmitt.words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This program reads the content of a text file, tokenizes the words, and counts the occurrences of each word.
 * It then displays the words and their counts in alphabetical order using a TreeMap.
 */
public class Words {
    public static void main(String[] args) {
        
        /**
         * REPLACE WITH THE DESIRED FILENAME:
         * 
         * OPTIONS:
         * [wiki-generative-ai.txt](Wikipedia article about generative ai)
         * [javasrc-1.txt](This java file in text format)
         * [javasrc-2.txt](Codetimer assignment java file in text format)
         * 
         * IF YOU GET A FILE NOT FOUND ERROR VERIFY THE FILES ARE NAMED CORRECTLY
         * AND ARE PLACED IN THE CURRENT WORKING DIRECTORY WHICH IS PRINTED TO
         * THE CONSOLE OUTPUT AFTER THE ERROR MESSAGE ON LINES 42-45
         */
        final String filePath = "wiki-generative-ai.txt";

        // Initialize a StringBuilder to store the content of the text file
        StringBuilder text = new StringBuilder();
        String content;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read the content of the text file line by line and append it to the StringBuilder
            while ((line = reader.readLine()) != null) {
                text.append(line).append("\n"); // Append each line to the StringBuilder
            }
        } catch (IOException e) {
            // Handle and display any IOException that occurs during file reading
            System.err.println("An error occurred while reading the file: " + e.getMessage());

            // Print the current working directory to assist with debugging
            System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        }

        // Convert the content of the StringBuilder to a string
        content = text.toString();

        // Create a TreeMap to hold words as keys and their counts as values
        Map<String, Integer> map = new TreeMap<>();

        // Tokenize the content into words based on whitespace and punctuation characters
        String[] words = content.split("[\\s+\\p{P}]");
        for (int i = 0; i < words.length; i++) {
            String key = words[i].toLowerCase();

            // Check if the word is not empty
            if (key.length() > 0) {
                if (!map.containsKey(key)) {
                    // If the word is not in the map, add it with a count of 1
                    map.put(key, 1);
                } else {
                    // If the word is already in the map, increment its count
                    int value = map.get(key);
                    value++;
                    map.put(key, value);
                }
            }
        }

        // Display each word and its count in alphabetical order
        map.forEach((k, v) -> System.out.println(k + "\t" + v));
    }
}
