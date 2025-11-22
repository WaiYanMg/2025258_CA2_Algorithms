/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2025258_ca2_algorithms;

/**
 *
 * @author Wai
 */
//TreeNode Class - Represents a node in the binary tree
//Each node stores an employee and references to left and right children
//Used for building organizational hierarchy
public class TreeNode {
  
    Employee employee;
    TreeNode left;
    TreeNode right;
    
    /**
     * Constructor - creates a new tree node with employee data
     * @param employee The employee to store in this node
     */
    public TreeNode(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}
