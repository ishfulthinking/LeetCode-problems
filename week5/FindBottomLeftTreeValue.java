/* 513. Find Bottom Left Tree Value (Medium) */

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
    int greatestDepth;
    int result;
        
    public int findBottomLeftValue(TreeNode root)
    {
        greatestDepth = 0;
        result = root.val;
        
        // We'll traverse leftward first to get the leftmost leaf no matter what.
        getBottomLeftLeaf(root.left, 1);
        getBottomLeftLeaf(root.right, 1);
        
        return result;
    }
    
    private void getBottomLeftLeaf(TreeNode curr, int row)
    {
        if (curr == null)
            return;
        // If we've hit a leaf, check its depth to see if we should update the answer.
        if (curr.left == null && curr.right == null)
        {
            if (row > greatestDepth)
            {
                result = curr.val;
                greatestDepth = row;
            }
            
            return;
        }
        // Check the children for a greater depth. We always check left first since we're finding the leftmost leaf.
        getBottomLeftLeaf(curr.left, row + 1);
        getBottomLeftLeaf(curr.right, row + 1);
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 38.8 MB (< 85.89%)
    Time complexity: O(n) since we check all n nodes only once in the worst case.
    Space complexity: O(1) since we only use 2 global variables through our whole solution.
*/