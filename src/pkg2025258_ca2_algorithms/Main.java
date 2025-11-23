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

    
    private DepartmentStore departmentStore; // Handles all business logic
    private Scanner scanner;                //For user input
    
     // Constructor - initialize components
     public Main() {
        departmentStore = new DepartmentStore();
        scanner = new Scanner(System.in);
    }
     
    // DISPLAY MAIN CONSOLE MENU SYSTEM
 
    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           DEPARTMENT STORE MANAGEMENT SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("1. LOAD DATA FROM FILE");
        System.out.println("2. SORT EMPLOYEES (Quick Sort)");
        System.out.println("3. SEARCH EMPLOYEE (Binary Search)");
        System.out.println("4. ADD NEW EMPLOYEE");
        System.out.println("5. BUILD ORGANIZATIONAL TREE");
        System.out.println("6. DISPLAY ALL EMPLOYEES");
        System.out.println("7. EXIT");
        System.out.println("=".repeat(60));
        System.out.print("Please select an option (1-7): ");
    }
    
    // Main program loop - continuously displays menu and handles user choices
 public void run() {
        System.out.println("Starting Employee Management System...");
        
        // Main program loop
        while (true) {
            displayMainMenu();
            String input = scanner.nextLine().trim();
            
            // Handle user selection
            switch (input) {
                case "1": loadDataFromFile(); break;
                case "2": sortEmployees(); break;
                case "3": searchEmployee(); break;
                case "4": addNewEmployee(); break;
                case "5": buildEmployeeTree(); break;
                case "6": displayAllEmployees(); break;
                case "7": exitProgram(); break;
                default: System.out.println("Invalid option! Please enter 1-7.");
            }
        }
    }
    
    // Option 1: Load employee data from file
    private void loadDataFromFile() {
        System.out.println("\n--- LOAD DATA ---");
        System.out.print("Enter filename: ");
        String filename = scanner.nextLine().trim();
        
        // Use default file if none provided
        if (filename.isEmpty()) {
            filename = "Applicants_Form.txt";
        }
        
        departmentStore.loadDataFromFile(filename);
    }
    
    //Option 2: Sort employees using Quick Sort algorithm
    private void sortEmployees() {
        System.out.println("\n--- SORT EMPLOYEES ---");
        departmentStore.sortEmployees();
    }
    
    // Option 3: Search for employee using Binary Search
    private void searchEmployee() {
        System.out.println("\n--- SEARCH EMPLOYEE ---");
        System.out.print("Enter last name to search: ");
        String searchName = scanner.nextLine().trim();
        
        if (searchName.isEmpty()) {
            System.out.println("Search name cannot be empty!");
            return;
        }
        
        departmentStore.searchEmployee(searchName);
    }
    
    // Option 4: Add a new employee to the system
    private void addNewEmployee() {
        System.out.println("\n--- ADD NEW EMPLOYEE ---");
        
        // Get required employee information with validation
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();
        if (firstName.isEmpty()) {
            System.out.println("First name is required!");
            return;
        }
        
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();
        if (lastName.isEmpty()) {
            System.out.println("Last name is required!");
            return;
        }
        
        System.out.print("Gender: ");
        String gender = scanner.nextLine().trim();
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if (!email.contains("@")) {
            System.out.println("Invalid email format!");
            return;
        }
        
        // Get and validate salary
        System.out.print("Salary: ");
        double salary;
        try {
            salary = Double.parseDouble(scanner.nextLine().trim());
            if (salary < 0) {
                System.out.println("Salary cannot be negative!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary format!");
            return;
        }
        
        // Department selection from predefined list
        System.out.println("\nAvailable Departments:");
        String[] departments = Department.getValidDepartments();
        for (int i = 0; i < departments.length; i++) {
            System.out.println((i + 1) + ". " + departments[i]);
        }
        System.out.print("Select department (1-9): ");
        String department;
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > departments.length) {
                System.out.println("Invalid department choice!");
                return;
            }
            department = departments[choice - 1];
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            return;
        }
        
        // Position selection (optional)
        System.out.println("\nAvailable Positions:");
        String[] positions = Manager.getValidPositions();
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i + 1) + ". " + positions[i]);
        }
        System.out.print("Select position (1-10, or Enter to skip): ");
        String positionInput = scanner.nextLine().trim();
        String position = "";
        if (!positionInput.isEmpty()) {
            try {
                int choice = Integer.parseInt(positionInput);
                if (choice >= 1 && choice <= positions.length) {
                    position = positions[choice - 1];
                }
            } catch (NumberFormatException e) {
                // Position remains empty if invalid input
            }
        }
        
        // Additional information
        System.out.print("Job Title: ");
        String jobTitle = scanner.nextLine().trim();
        
        System.out.print("Company: ");
        String company = scanner.nextLine().trim();
        
        // Add the new employee to the system
        departmentStore.addNewEmployee(firstName, lastName, gender, email, salary, 
                                     department, position, jobTitle, company);
    }
    
    //Option 5: Build organizational hierarchy tree
    private void buildEmployeeTree() {
        System.out.println("\n--- BUILD ORGANIZATIONAL TREE ---");
        departmentStore.buildEmployeeTree();
    }
    
    //Option 6: Display all employees in the system
    private void displayAllEmployees() {
        System.out.println("\n--- DISPLAY ALL EMPLOYEES ---");
        departmentStore.displayAllEmployees();
    }
    
    //Option 7: Exit the application
    private void exitProgram() {
        System.out.println("\nThank you for using Employee Management System!");
        System.out.println("Goodbye!");
        System.exit(0);
    }
    
    //     MAIN 
    public static void main(String[] args) {
        Main console_app=new Main();
        console_app.run();
    }
    
}
