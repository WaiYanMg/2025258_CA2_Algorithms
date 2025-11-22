/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2025258_ca2_algorithms;

/**
 *
 * @author Wai
 */
//Department Class - Handles department validation and management
// Defines all valid departments in the organization

public class Department {
    
    private static final String[] VALID_DEPARTMENTS = {
        "IT Development", "Sales", "HR", "Finance", "Marketing", 
        "Technical Support", "Customer Service", "Accounting", "Operations"
    };
    
        
    /**
     * Get a copy of all valid department names
     * @return Array of valid department strings
     */
    public static String[] getValidDepartments() {
        return VALID_DEPARTMENTS.clone();
    }
    
     /**
     * Check if a department is valid
     * @param department The department to validate
     * @return true if department is valid, false otherwise
     */
    public static boolean isValidDepartment(String department) {
        for (String validDept : VALID_DEPARTMENTS) {
            if (validDept.equalsIgnoreCase(department)) {
                return true;
            }
        }
        return false;
    }
    
    
}
