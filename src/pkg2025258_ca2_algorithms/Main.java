/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2025258_ca2_algorithms;

import java.util.Scanner;

/**
 *
 * @author Wai
 */

/*
App Class - Main console menu interface
Provides user-friendly menu navigation for Department Store System
 */

public class Main {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initialize scanner and store
        Scanner scanner = new Scanner(System.in);
        DepartmentStore store = new DepartmentStore();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           DEPARTMENT STORE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        
        // Keep asking for filename until valid file is loaded
        boolean fileLoaded = false;
        while (!fileLoaded) {
            System.out.print("Please enter the filename to read (Applicants_Form.txt or Applicants_Form): ");
            String filename = scanner.nextLine().trim();
            
            // Auto add .txt extension if not included
            if (!filename.endsWith(".txt")) {
                filename = filename + ".txt";
            }
            
            // Try to load the data from file
            fileLoaded = store.loadDataFromFile(filename);
            
            if (!fileLoaded) {
                System.out.println("Please try again.\n");
            }
        }
        
        // Menu loop - keeps running until user exits
        boolean running = true;
        while (running) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("Do You wish to SORT or SEARCH:");
            System.out.println("1. SORT (Quick Sort)");
            System.out.println("2. SEARCH (Binary Search)");
            System.out.println("3. ADD RECORDS");
            System.out.println("4. BUILD ORGANIZATIONAL TREE");
            System.out.println("5. DISPLAY ALL EMPLOYEES");
            System.out.println("6. EXIT");
            System.out.println("=".repeat(60));
            System.out.print("Please select an option (1-6): ");
            
            // Get user input
            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }
            
            // Process user choice
            switch (choice) {
                case 1:
                    // SORT using Quick Sort
                    store.sortEmployees();
                    break;
                    
                case 2:
                    // SEARCH using Binary Search
                    System.out.print("Enter last name to search: ");
                    String searchName = scanner.nextLine().trim();
                    store.searchEmployee(searchName);
                    break;
                    
                case 3:
                    // ADD NEW EMPLOYEE
                    addNewEmployee(scanner, store);
                    break;
                    
                case 4:
                    // BUILD ORGANIZATIONAL TREE
                    store.buildEmployeeTree();
                    break;
                    
                case 5:
                    // DISPLAY ALL EMPLOYEES
                    store.displayAllEmployees();
                    break;
                    
                case 6:
                    // EXIT PROGRAM
                    System.out.println("Thank you for using the system. Goodbye!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Invalid option! Please select 1-6.");
            }
        }
        scanner.close();
    }
    
    /**
     * Helper method to add a new employee
     * Collects all required information from user
     * @param scanner Scanner for user input
     * @param store DepartmentStore to add employee to
     */
    private static void addNewEmployee(Scanner scanner, DepartmentStore store) {
        System.out.println("\n=== ADD NEW EMPLOYEE ===");
        
        // Collect employee details
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.nextLine().trim();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        
        // Get salary with validation
        System.out.print("Enter Salary: ");
        double salary = 0;
        try {
            salary = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary! Setting to 0.");
        }
        
        // Show valid departments and get input
        Department.displayDepartments();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine().trim();
        
        // Show valid positions and get input
        Manager.displayPositions();
        System.out.print("Enter Position (or press Enter to skip): ");
        String position = scanner.nextLine().trim();
        
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine().trim();
        
        System.out.print("Enter Company: ");
        String company = scanner.nextLine().trim();
        
        // Add employee to store
        store.addNewEmployee(firstName, lastName, gender, email, 
                            salary, department, position, jobTitle, company);
    }
}
