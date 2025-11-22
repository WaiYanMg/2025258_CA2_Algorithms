/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2025258_ca2_algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author Wai
 */
public class DepartmentStore {
    
    private ArrayList<Employee> employees;    // Stores all employee records
    private EmployeeBinaryTree employeeTree;  // For organizational hierarchy
    private boolean isDataLoaded;             // Flag to check if data is loaded
    
    // Constructor - initialize empty data structures
    public DepartmentStore() {
        employees = new ArrayList<>();
        employeeTree = new EmployeeBinaryTree();
        isDataLoaded = false;
    }
    
    /**
     * Load employee data from CSV file
     * @param filename The name of the file to load
     */
    public void loadDataFromFile(String filename) {
        BufferedReader fileReader = null;
        int successfulRecords = 0;
        
        try {
            // Simple file path - same folder as Java files
            File file = new File(filename);
            
            if (!file.exists()) {
                System.out.println("File not found: " + file.getAbsolutePath());
                return;
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
            
            // Auto-sort data after loading
            if (!employees.isEmpty()) {
                System.out.println("Auto-sorting loaded data...");
                quickSort(employees, 0, employees.size() - 1);
                displayFirst20Employees();
            }
            
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        } finally {
            // Ensure file is closed properly
            closeFileReader(fileReader);
        }
    }

    private void displayFirst20Employees() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void quickSort(ArrayList<Employee> employees, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private boolean processDataLine(String currentLine, int lineNumber) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void closeFileReader(BufferedReader fileReader) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
