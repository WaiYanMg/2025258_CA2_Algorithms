/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2025258_ca2_algorithms;

/**
 *
 * @author Wai
 */
//Manager Class - Handles position validation and management
//Defines all valid position types in the organization

public class Manager {
    

    // Array of valid position types in the organization
    private static final String[] VALID_POSITIONS = {
        "senior", "middle", "junior", "intern", "contract", "Manager", 
        "Assistant Manager", "Senior Manager", "Team Lead", "Head Manager"
    };
    
     /**
     * Get a copy of all valid position types
     * @return Array of valid position strings
     */
    public static String[] getValidPositions() {
        return VALID_POSITIONS.clone();
    }
    
    /**
     * Check if a position is valid
     * @param position The position to validate
     * @return true if position is valid or empty, false otherwise
     */
    public static boolean isValidPosition(String position) {
        if (position == null || position.isEmpty()) return true;
        for (String validPos : VALID_POSITIONS) {
            if (validPos.equalsIgnoreCase(position)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Display all valid positions to the user
     * Used when adding new employees
     */
    public static void displayPositions() {
        System.out.println("\n=== VALID POSITIONS ===");
        for (int i = 0; i < VALID_POSITIONS.length; i++) {
            System.out.println((i + 1) + ". " + VALID_POSITIONS[i]);
        }
    }
}
