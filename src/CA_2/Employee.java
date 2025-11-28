/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author Wai
 */

/**
 * Employee Class - Represents an employee with personal and professional details
 */
public class Employee {
    
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private double salary;
    private String department;
    private String position;
    private String jobTitle;
    private String company;
    
    /**
     * Constructor - creates a new Employee object
     * @param firstName Employee's first name
     * @param lastName Employee's last name (used for sorting)
     * @param gender Employee's gender
     * @param email Employee's email address
     * @param salary Employee's salary
     * @param department Employee's department
     * @param position Employee's position level
     * @param jobTitle Employee's job title
     * @param company Employee's company
     */
    public Employee(String firstName, String lastName, String gender, String email, 
                   double salary, String department, String position, String jobTitle, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.salary = salary;
        this.department = department;
        this.position = position;
        this.jobTitle = jobTitle;
        this.company = company;
    }
    
    // GETTER METHODS
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getEmail() { return email; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public String getJobTitle() { return jobTitle; }
    public String getCompany() { return company; }
    
    
    /**
    * Get full name by combining first and last name
    * @return Full name as "FirstName LastName"
    */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    /**
     * Convert employee to detailed string representation
     * Shows all employee attributes in formatted columns
     * @return Formatted string with all employee details
     */
    @Override
    public String toString() {
        return String.format("%-12s %-12s %-8s %-25s %-10.2f %-20s %-12s %-25s %-15s", 
                           firstName, lastName, gender, email, salary, 
                           department, position, jobTitle, company);
    }
    
     /**
     * Convert employee to compact string representation
     * Shows only essential information for lists and tables
     * Used when displaying multiple employees
     * @return Compact formatted string with key details
     */
    public String toCompactString() {
        return String.format("%-12s %-12s %-20s %-12s %-10.2f", 
                           firstName, lastName, department, 
                           position.isEmpty() ? "N/A" : position, salary);
    }
}
