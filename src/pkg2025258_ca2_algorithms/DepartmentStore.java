/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2025258_ca2_algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wai
 */
/**
 * Core business logic class - handles all data processing and algorithms
 * Implements Quick Sort, Binary Search, and file operations
 */
public class DepartmentStore {
    
    private ArrayList<Employee> employees;    // Stores all employee records
    private EmployeeBinaryTree employeeTree;  // For organizational hierarchy
    private boolean isDataLoaded;             // Flag to check if data is loaded
    private boolean isSorted;                 // Flag to check if data is sorted
    
    // Constructor - initialize empty data structures
    public DepartmentStore() {
        employees = new ArrayList<>();
        employeeTree = new EmployeeBinaryTree();
        isDataLoaded = false;
        isSorted = false;
    }
    
    /**
     * Load employee data from CSV file
     * @param filename The name of the file to load
     * @return true if file loaded successfully, false otherwise
     */
    public boolean loadDataFromFile(String filename) {
        BufferedReader fileReader = null;
        int successfulRecords = 0;
        
        try {
            // Simple file path - same folder as Java files
            File file = new File(filename);
            
            if (!file.exists()) {
                System.out.println("File not found: " + file.getAbsolutePath());
                return false;
            }
            
            // Open file for reading
            fileReader = new BufferedReader(new FileReader(file));
            System.out.println("Reading file: " + file.getAbsolutePath());
            
            String currentLine;
            int lineNumber = 0;
            
            // Clear any existing data
            employees.clear();
            
            // Read file line by line
            while ((currentLine = fileReader.readLine()) != null) {
                lineNumber++;
                
                // Skip header row (first line)
                if (lineNumber == 1) {
                    System.out.println("Header: " + currentLine);
                    continue;
                }
                
                // Skip empty lines
                if (currentLine.trim().isEmpty()) {
                    continue;
                }
                
                // Process each data line
                if (processDataLine(currentLine, lineNumber)) {
                    successfulRecords++;
                }
            }
            
            System.out.println("File loaded successfully!");
            System.out.println("Loaded " + successfulRecords + " employee records");
            isDataLoaded = true;
            isSorted = false;  // Data is NOT sorted when first loaded
            
            // Display original data (unsorted) after loading
            if (!employees.isEmpty()) {
                System.out.println("\nDisplaying ORIGINAL data (not sorted)...");
                displayAllEmployees();
            }
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            return false;
        } finally {
            // Ensure file is closed properly
            closeFileReader(fileReader);
        }
    }

    /**
     * Sort employees using Quick Sort algorithm
     * Time Complexity: O(n log n) average case
     */
    public void sortEmployees() {
        if (!isDataLoaded || employees.isEmpty()) {
            System.out.println("No data loaded! Please load data first.");
            return;
        }
        
        // Check if already sorted
        if (isSorted) {
            System.out.println("Data is already sorted!");
            displayAllEmployees();
            return;
        }
        
        System.out.println("Sorting " + employees.size() + " employees using Quick Sort...");
        
        // Measure sorting performance
        long startTime = System.currentTimeMillis();
        quickSort(employees, 0, employees.size() - 1);
        long endTime = System.currentTimeMillis();
        
        isSorted = true;  // Mark data as sorted
        System.out.println("Sorting completed in " + (endTime - startTime) + " ms");
        System.out.println("\nDisplaying SORTED data:");
        displayAllEmployees();
    }
    
     /**
     * Search for employee by last name using Binary Search
     * Requires sorted data - Time Complexity: O(log n)
     * @param searchName The last name to search for
     */
    public void searchEmployee(String searchName) {
        if (!isDataLoaded || employees.isEmpty()) {
            System.out.println("No data loaded! Please load data first.");
            return;
        }
        
        // Ensure data is sorted for binary search to work
        if (!isSorted) {
            System.out.println("Data must be sorted before searching. Sorting now...");
            quickSort(employees, 0, employees.size() - 1);
            isSorted = true;
        }
        
        // Perform binary search
        List<Employee> foundEmployees = performBinarySearch(searchName);
        
        // Display results
        if (!foundEmployees.isEmpty()) {
            System.out.println("\nFOUND " + foundEmployees.size() + " EMPLOYEE(S):");
            System.out.println("=".repeat(120));
            System.out.printf("%-15s %-15s %-20s %-15s %-30s %-12s\n", 
                "First Name", "Last Name", "Department", "Position", "Email", "Salary");
            System.out.println("=".repeat(120));
            
            for (Employee emp : foundEmployees) {
                System.out.printf("%-15s %-15s %-20s %-15s %-30s $%-11.2f\n",
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getDepartment(),
                    emp.getPosition().isEmpty() ? "N/A" : emp.getPosition(),
                    emp.getEmail(),
                    emp.getSalary());
            }
        } else {
            System.out.println("No employees found with last name: " + searchName);
        }
    }
    
    /**
     * Add a new employee to the system
     * Displays employee details VERTICALLY (one field per line)
     */
    public void addNewEmployee(String firstName, String lastName, String gender, String email,
                             double salary, String department, String position, 
                             String jobTitle, String company) {
        // Create new employee object
        Employee newEmployee = new Employee(firstName, lastName, gender, email, 
                                          salary, department, position, jobTitle, company);
        employees.add(newEmployee);
        isDataLoaded = true;
        isSorted = false;  // Data is no longer sorted after adding new record
        
        // Display the newly added employee details VERTICALLY
        System.out.println("\n=== EMPLOYEE ADDED SUCCESSFULLY! ===");
        System.out.println("First Name: " + newEmployee.getFirstName());
        System.out.println("Last Name: " + newEmployee.getLastName());
        System.out.println("Gender: " + newEmployee.getGender());
        System.out.println("Email: " + newEmployee.getEmail());
        System.out.println("Salary: $" + String.format("%.2f", newEmployee.getSalary()));
        System.out.println("Department: " + newEmployee.getDepartment());
        System.out.println("Position: " + (newEmployee.getPosition().isEmpty() ? "N/A" : newEmployee.getPosition()));
        System.out.println("Job Title: " + newEmployee.getJobTitle());
        System.out.println("Company: " + newEmployee.getCompany());
        System.out.println("=".repeat(40));
        System.out.println("Note: Data is now UNSORTED. Use option 1 to sort the data.");
        System.out.println("Total employees: " + employees.size());
    }
    
    /**
     * Build organizational hierarchy using binary tree with level-order insertion
     */
    public void buildEmployeeTree() {
        if (!isDataLoaded || employees.isEmpty()) {
            System.out.println("No data loaded! Please load data first.");
            return;
        }
        
        employeeTree = new EmployeeBinaryTree();
        
        // Use ALL employees for tree
        int treeSize = employees.size();
        System.out.println("Inserting " + treeSize + " employees using level-order insertion...");
        
        // Insert employees into tree
        for (int i = 0; i < treeSize; i++) {
            employeeTree.levelOrderInsert(employees.get(i));
        }
        
        // Display the organizational hierarchy
        employeeTree.displayHierarchy();
    }
    
    /**
     * Display all employees in the system (in current order - sorted or unsorted)
     */
    public void displayAllEmployees() {
        if (!isDataLoaded || employees.isEmpty()) {
            System.out.println("No data loaded! Please load data first.");
            return;
        }
        
        // Show status: sorted or original order
        String status = isSorted ? "SORTED" : "ORIGINAL ORDER";
        System.out.println("\nALL EMPLOYEES - " + status + " (" + employees.size() + " records)");
        System.out.println("=".repeat(120));
        System.out.printf("%-15s %-15s %-20s %-15s %-30s %-12s\n", 
            "First Name", "Last Name", "Department", "Position", "Email", "Salary");
        System.out.println("=".repeat(120));
        
        // Display each employee in compact format
        for (Employee emp : employees) {
            System.out.printf("%-15s %-15s %-20s %-15s %-30s $%-11.2f\n",
                emp.getFirstName(),
                emp.getLastName(),
                emp.getDepartment(),
                emp.getPosition().isEmpty() ? "N/A" : emp.getPosition(),
                emp.getEmail(),
                emp.getSalary());
        }
        
        System.out.println("=".repeat(120));
        System.out.println("Total records displayed: " + employees.size());
    }
    
    // CORE ALGORITHM IMPLEMENTATIONS
    
    /**
     * Quick Sort algorithm implementation
     * Recursively sorts employees by last name
     * @param employeeList The list to sort
     * @param lowIndex Starting index for sorting
     * @param highIndex Ending index for sorting
     */
    public void quickSort(ArrayList<Employee> employeeList, int lowIndex, int highIndex) {
        
        // Base case: if the segment has more than one element
        if (lowIndex < highIndex) {
            // Partition the array and get pivot position
            int partitionIndex = partition(employeeList, lowIndex, highIndex);
            
            // Recursively sort elements before and after partition
            quickSort(employeeList, lowIndex, partitionIndex - 1);
            quickSort(employeeList, partitionIndex + 1, highIndex);
        }
    }
    
    /**
     * Partition method for Quick Sort
     * Selects last element as pivot and partitions array around it
     * @return The final position of the pivot element
     */
    private int partition(ArrayList<Employee> employeeList, int lowIndex, int highIndex) {
        // Use last name of last element as pivot
        String pivotValue = employeeList.get(highIndex).getLastName();
        int smallerElementIndex = lowIndex - 1;  // Index of smaller element
        
        // Iterate through array segment
        for (int currentIndex = lowIndex; currentIndex < highIndex; currentIndex++) {
            // If current element is smaller than or equal to pivot
            if (employeeList.get(currentIndex).getLastName().compareToIgnoreCase(pivotValue) <= 0) {
                smallerElementIndex++;
                // Swap current element with element at smaller index
                swapEmployees(employeeList, smallerElementIndex, currentIndex);
            }
        }
        
        // Place pivot in correct position
        swapEmployees(employeeList, smallerElementIndex + 1, highIndex);
        return smallerElementIndex + 1;
    }
    
    /**
     * Swap two employees in the list
     */
    private void swapEmployees(ArrayList<Employee> employeeList, int firstIndex, int secondIndex) {
        Employee temp = employeeList.get(firstIndex);
        employeeList.set(firstIndex, employeeList.get(secondIndex));
        employeeList.set(secondIndex, temp);
    }
    
     /**
     * Binary Search algorithm implementation
     * Finds all employees with matching last name (handles duplicates)
     * @param searchName The last name to search for
     * @return List of found employees
     */
    private List<Employee> performBinarySearch(String searchName) {
        int left = 0;
        int right = employees.size() - 1;
        List<Employee> foundEmployees = new ArrayList<>();
        
        // Binary search loop
        while (left <= right) {
            int mid = left + (right - left) / 2;  // Avoid overflow
            Employee midEmployee = employees.get(mid);
            int comparison = searchName.compareToIgnoreCase(midEmployee.getLastName());
            
            if (comparison == 0) {
                // Found matching employee - collect all duplicates
                collectAllMatchingEmployees(foundEmployees, mid, searchName);
                break;
            } else if (comparison < 0) {
                // Search in left half
                right = mid - 1;
            } else {
                // Search in right half
                left = mid + 1;
            }
        }
        
        return foundEmployees;
    }
    
    /**
     * Collect all employees with matching last name (handles duplicates)
     */
    private void collectAllMatchingEmployees(List<Employee> foundEmployees, int middleIndex, String searchName) {
        // Add the initially found employee
        foundEmployees.add(employees.get(middleIndex));
        
        // Check left side for duplicates
        int leftIndex = middleIndex - 1;
        while (leftIndex >= 0 && employees.get(leftIndex).getLastName().equalsIgnoreCase(searchName)) {
            foundEmployees.add(employees.get(leftIndex));
            leftIndex--;
        }
        
        // Check right side for duplicates
        int rightIndex = middleIndex + 1;
        while (rightIndex < employees.size() && employees.get(rightIndex).getLastName().equalsIgnoreCase(searchName)) {
            foundEmployees.add(employees.get(rightIndex));
            rightIndex++;
        }
    }
    
    // HELPER METHODS
    
    /**
     * Process a single line from the data file
     * @return true if line was processed successfully, false otherwise
     */
    private boolean processDataLine(String line, int lineNumber) {
        try {
            // Split CSV line into fields
            String[] fields = line.split(",");
            
            if (fields.length < 9) {
                System.out.println("Line " + lineNumber + ": Insufficient fields, skipping");
                return false;
            }
            
            // Extract and trim all fields
            String firstName = fields[0].trim();
            String lastName = fields[1].trim();
            String gender = fields[2].trim();
            String email = fields[3].trim();
            double salary = Double.parseDouble(fields[4].trim());
            String department = fields[5].trim();
            String position = fields[6].trim();
            String jobTitle = fields[7].trim();
            String company = fields[8].trim();
            
            // Validate required fields
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                System.out.println("Line " + lineNumber + ": Missing required fields, skipping");
                return false;
            }
            
            // Create and add employee object
            Employee employee = new Employee(firstName, lastName, gender, email, 
                                           salary, department, position, jobTitle, company);
            employees.add(employee);
            return true;
            
        } catch (Exception e) {
            System.out.println("Line " + lineNumber + ": Error processing, skipping");
            return false;
        }
    }
    
    /**
     * Safely close the file reader
     */
    private void closeFileReader(BufferedReader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}
