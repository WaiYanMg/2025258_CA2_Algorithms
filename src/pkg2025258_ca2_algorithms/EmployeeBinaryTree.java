/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2025258_ca2_algorithms;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Wai
 */

/**
 * EmployeeBinaryTree class - implements binary tree with level-order insertion
 * Creates a balanced organizational hierarchy representation
 * Why level-order? Creates a balanced tree that represents organizational structure naturally
 */

public class EmployeeBinaryTree {
    
    
    private TreeNode root;       // Root node of the tree
    private int nodeCount;      // Total number of nodes in tree
    private int maxHeight;      // Maximum height/depth of tree
    
     /**
     * Constructor - initialize empty tree
     */
    public EmployeeBinaryTree() {
        root = null;
        nodeCount = 0;
        maxHeight = 0;
    }
    
    /**
     * Insert employee using level-order insertion
     * This creates a balanced tree by filling each level completely before moving to next
     * Time Complexity: O(n) for insertion
     * @param employee The employee to insert into the tree
     */
    public void levelOrderInsert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);
        
        // If tree is empty, new node becomes root
        if (root == null) {
            root = newNode;
            nodeCount++;
            return;
        }
        
        // Use queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Traverse tree to find insertion position
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            
            
            // Insert as left child if available
            if (current.left == null) {
                current.left = newNode;
                nodeCount++;
                return;
            } else {
                queue.add(current.left);
            }
            
            // Insert as right child if available
            if (current.right == null) {
                current.right = newNode;
                nodeCount++;
                return;
            } else {
                queue.add(current.right);
            }
        }
    }
    
     /**
     * Display the organizational hierarchy using level-order traversal
     * Shows employees grouped by their level in the tree
     */
    public void displayHierarchy() {
        if (root == null) {
            System.out.println("Tree is empty!");
            return;
        }
        
        System.out.println("\nEMPLOYEE HIERARCHY (Level Order)");
        System.out.println("=".repeat(85));
        System.out.printf("%-5s %-12s %-12s %-20s %-12s %-10s\n", 
                         "Level", "First Name", "Last Name", "Department", "Position", "Salary");
        System.out.println("=".repeat(85));
        
        // Use queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        maxHeight = 0;
        
        //Traverse tree level by level

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at current level
            System.out.println("Level " + level + ":");
            
            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                Employee emp = current.employee;
                
                // Display employee information
                System.out.printf("       %-12s %-12s %-20s %-12s $%-9.2f\n",
                    emp.getFirstName(),
                    emp.getLastName(),
                    emp.getDepartment(),
                    emp.getPosition().isEmpty() ? "N/A" : emp.getPosition(),
                    emp.getSalary());
                
                // Add children to queue for next level processing
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            level++;
        }
        // Calculate and display tree statistics
        maxHeight = level;
        System.out.println("\nTree Statistics:");
        System.out.println("Total nodes: " + nodeCount);
        System.out.println("Maximum height: " + maxHeight);
    }
}
