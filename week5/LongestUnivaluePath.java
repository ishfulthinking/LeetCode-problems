/* 687. Longest Univalue Path (Easy) */

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
    int longestPath;
    
    public int longestUnivaluePath(TreeNode root)
    {
        longestPath = 0;
        
        getPath(root);
        
        return longestPath;
    }
    
    private int getPath(TreeNode curr)
    {
        // If the current node is null, check if we've hit the end of the longest path yet.
        if (curr == null)
            return 0;
        
        int leftPath = getPath(curr.left);
        int rightPath = getPath(curr.right);
        
        // If the left child has the same value as the current, increase the size of leftPath.
        if (curr.left != null && curr.left.val == curr.val)
            leftPath++;
        // Otherwise, we can reset that path.
        else
            leftPath = 0;
        // Same with the right child.
        if (curr.right != null && curr.right.val == curr.val)
            rightPath++;
        else
            rightPath = 0;
        
        longestPath = Math.max(longestPath, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
}

/*
    Runtime: 4 ms (< 70.47%)
    Memory usage: 40.8 MB (< 96.36%)
    Time complexity: O(n) since we iterate through all n nodes in the tree.
    Space complexity: O(n) since we create two new ints for every node in the tree.
*/