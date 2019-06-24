/* 543. Diameter of Binary Tree */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution
{
    int maxDepth;
    
    public int diameterOfBinaryTree(TreeNode root)
    {
        // For this problem, we can get the maximum depth of any path on the tree,
        // and when encountering a node with 2 children, sum the greatest depths of its children.
        maxDepth = 0;
        
        // Recursively get the depth of the children of every node.
        getDepth(root);
        
        return maxDepth;
    }
    
    private int getDepth(TreeNode curr)
    {
        if (curr == null)
            return 0;
        
        // First, get the max depth of each of the children of the current node.
        int leftDepth = getDepth(curr.left);
        int rightDepth = getDepth(curr.right);
        // Update the max distance if the distance between the children of the current node is higher.
        maxDepth = Math.max(maxDepth, leftDepth + rightDepth);
        // Return the higher depth of the children, plus 1 for the current node, to its parent caller.
        return Math.max(leftDepth, rightDepth) + 1;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 39 MB (< 40.76%)
    Time complexity: O(n) since we go through all n nodes at most.
    Space complexity: O(n) since we create two new ints for every node in the tree.
*/