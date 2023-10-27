/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.schmitt.phonebook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.util.Scanner;

public class Phonebook {

    public static void main(String[] args) {
        BuildUI();
        LoadPhonebook();
        Run();
    }

    private static Scanner scanner = new Scanner(System.in);
    private static Scanner selectionScanner = new Scanner(System.in);

    private static StringBuilder mainMenu = new StringBuilder();
    private static Hashtable<String, String> phonebook = new Hashtable<>();

    private static void BuildUI() {
        mainMenu.append("Phonebook Menu:\n");
        mainMenu.append("1. Add a contact\n");
        mainMenu.append("2. Search for a contact\n");
        mainMenu.append("3. Edit a contact\n");
        mainMenu.append("4. Print the phonebook\n");
        mainMenu.append("5. Save the phonebook\n");
        mainMenu.append("6. Exit\n");
        mainMenu.append('\n');
    }

    // Function to load the phonebook from the "phonebook.dat" file
    private static void LoadPhonebook() {
        var loadedPhonebook = new Hashtable<String, String>();

        try {
            FileInputStream fileIn = new FileInputStream("phonebook.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            loadedPhonebook = (Hashtable<String, String>) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (Exception e) {
            System.out.println("Error loading phonebook: " + e.getMessage());
        }

        phonebook = loadedPhonebook;

    }

    private static void Run() {
        String strMenu = mainMenu.toString();

        while (true) {
            System.out.println(strMenu);

            boolean invalidOption = true;
            int choice = 0;

            while (invalidOption) {
                try {
                    System.out.println("Please select an option:");

                    int selection = selectionScanner.nextInt();

                    if (selection > 0 && selection <= 6) {
                        choice = selection;
                        invalidOption = false;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Input not valid!");
                    selectionScanner.next();
                } catch (NoSuchElementException ex) {
                    System.out.println("Input not valid!");
                    selectionScanner.next();
                }
            }

            switch (choice) {
                //Add a contact
                case 1 -> {
                    System.out.println("Enter phone number");
                    String phoneNumber = scanner.nextLine();
                    System.out.println("Enter contact information");
                    String contactInfo = scanner.nextLine();
                    phonebook.put(phoneNumber, contactInfo);
                }

                //Search for a contact
                case 2 -> {
                    System.out.println("Enter a phone number or contact information");
                    String searchKey = scanner.nextLine();
                    String result = phonebook.get(searchKey);

                    if (result != null) {
                        System.out.println("Contact found: " + result);
                    } else {
                        System.out.println("No contact found");
                    }
                }

                //Edit the contact
                case 3 -> {
                    System.out.print("Enter a phone number to edit the contact: ");
                    String editNumber = scanner.nextLine();
                    String existingContact = phonebook.get(editNumber);

                    if (existingContact != null) {
                        System.out.println("Current contact: " + existingContact);
                        System.out.print("Enter the new contact information: ");
                        String newContactInfo = scanner.nextLine();
                        phonebook.put(editNumber, newContactInfo);
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Contact not found. Cannot edit.");
                    }
                }

                //Print the phonebook
                case 4 -> {
                    for (String key : phonebook.keySet()) {
                        System.out.println(key + ": " + phonebook.get(key));
                    }
                }

                //Save the phonebook
                case 5 -> {
                    try {
                        FileOutputStream fileOut = new FileOutputStream("phonebook.dat");
                        var objectOut = new ObjectOutputStream(fileOut);
                        objectOut.writeObject(phonebook);
                        objectOut.close();
                        fileOut.close();
                        System.out.println("Phonebook saved to phonebook.dat.");
                    } catch (Exception e) {
                        System.out.println("Error saving phonebook: " + e.getMessage());
                    }
                }

                //Exit the program
                case 6 -> {
                    System.out.println("Exiting...");
                    selectionScanner.close();
                    scanner.close();
                    System.exit(0);
                }

                default -> {
                    System.out.println("Invalid option selected");
                }

            }
        }
    }
}
