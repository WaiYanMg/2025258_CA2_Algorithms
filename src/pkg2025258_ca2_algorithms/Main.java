/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2025258_ca2_algorithms;

import java.util.Scanner;
import java.util.regex.Pattern;

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
     * Collects all required information from user with validation
     * @param scanner Scanner for user input
     * @param store DepartmentStore to add employee to
     */
    private static void addNewEmployee(Scanner scanner, DepartmentStore store) {
        System.out.println("\n=== ADD NEW EMPLOYEE ===");
        
        // Collect employee details with validation
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.print("Enter Gender (Male/Female): ");
        String gender = scanner.nextLine().trim();
        
        // Email validation with loop (NO yes/no prompt - just like salary)
        String email = "";
        boolean validEmail = false;
        while (!validEmail) {
            System.out.print("Enter Email: ");
            email = scanner.nextLine().trim();
            
            // Validate email format
            if (isValidEmail(email)) {
                validEmail = true;
            } else {
                System.out.println("Invalid email format! Please use format: name@domain.com");
            }
        }
        
        // Salary validation with loop
        double salary = 0;
        boolean validSalary = false;
        while (!validSalary) {
            System.out.print("Enter Salary: ");
            try {
                String salaryInput = scanner.nextLine().trim();
                salary = Double.parseDouble(salaryInput);
                
                // Check if salary is positive
                if (salary > 0) {
                    validSalary = true;
                } else {
                    System.out.println("Salary must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary! Please enter a valid number.");
            }
        }
        
        // Show valid departments and get selection by NUMBER
        Department.displayDepartments();
        System.out.print("Select Department (enter number 1-9): ");
        String department = "";
        try {
            int deptChoice = Integer.parseInt(scanner.nextLine().trim());
            String[] validDepts = Department.getValidDepartments();
            
            // Validate department choice
            if (deptChoice >= 1 && deptChoice <= validDepts.length) {
                department = validDepts[deptChoice - 1];  // Array is 0-indexed
                System.out.println("Selected: " + department);
            } else {
                System.out.println("Invalid department number! Using 'IT Development' as default.");
                department = "IT Development";
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Using 'IT Development' as default.");
            department = "IT Development";
        }
        
        // Show valid positions and get selection by NUMBER
        Manager.displayPositions();
        System.out.print("Select Position (enter number 1-10, or press Enter to skip): ");
        String positionInput = scanner.nextLine().trim();
        String position = "";
        
        // Allow user to skip position by pressing Enter
        if (!positionInput.isEmpty()) {
            try {
                int posChoice = Integer.parseInt(positionInput);
                String[] validPositions = Manager.getValidPositions();
                
                // Validate position choice
                if (posChoice >= 1 && posChoice <= validPositions.length) {
                    position = validPositions[posChoice - 1];  // Array is 0-indexed
                    System.out.println("Selected: " + position);
                } else {
                    System.out.println("Invalid position number! Leaving position empty.");
                    position = "";
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Leaving position empty.");
                position = "";
            }
        }
        
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine().trim();
        
        System.out.print("Enter Company: ");
        String company = scanner.nextLine().trim();
        
        // Add employee to store and display the added employee details
        store.addNewEmployee(firstName, lastName, gender, email, 
                            salary, department, position, jobTitle, company);
    }
    
    /**
     * Validate email format
     * @param email The email to validate
     * @return true if email is valid, false otherwise
     */
    private static boolean isValidEmail(String email) {
        // Simple email pattern: must contain @ and . after @
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
