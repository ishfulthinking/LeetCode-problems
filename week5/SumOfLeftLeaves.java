/* 404. Sum of Left Leaves */

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
    public int sumOfLeftLeaves(TreeNode root)
    {
        if (root == null)
            return 0;
        
        int sum = 0;
        sum += sumLeaves(root.left, true);
        sum += sumLeaves(root.right, false);
        
        return sum;
    }
    
    private int sumLeaves(TreeNode curr, boolean left)
    {
        if (curr == null)
            return 0;
        
        // If the curr node is a leaf, check if it's a left one before returning a value.
        if (curr.left == null && curr.right == null)
        {
            if (left)
                return curr.val;
            else
                return 0;
        }
        
        int sum = 0;
        sum += sumLeaves(curr.left, true);
        sum += sumLeaves(curr.right, false);
        
        return sum;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 35.5 MB (< 100%)
    Time complexity: O(n) since we go through all n nodes in the tree.
    Space complexity: O(n) since we create a new int variable for every non-leaf node.
*/