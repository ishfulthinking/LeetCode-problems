/* 100. Same Tree (Easy) */

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
    public boolean isSameTree(TreeNode p, TreeNode q)
    {
        List<Integer> tree1 = new ArrayList<>();
        List<Integer> tree2 = new ArrayList<>();
        
        getTreeLayout(tree1, p);
        getTreeLayout(tree2, q);
        
        if (tree1.size() != tree2.size())
            return false;
        
        // Go through every object in each tree to check for equality in order.
        for (int i = 0, size = tree1.size(); i < size; i++)
        {
            // If one but not the other is null, return false.
            if ((tree1.get(i) == null && tree2.get(i) != null) || (tree1.get(i) != null && tree2.get(i) == null))
                return false;
            // If they're both null, continue.
            if (tree1.get(i) == null && tree2.get(i) == null)
                continue;
            // Otherwise, we have two integers, so we have to check for value equality, not object reference equality.
            if (tree1.get(i).intValue() != tree2.get(i).intValue())
                return false;
        }
        
        return true;
    }
    
    // Add to the tree list preorder.
    private void getTreeLayout(List<Integer> tree, TreeNode curr)
    {
        if (curr == null)
        {
            tree.add(null);
            return;
        }
        
        tree.add(curr.val);
        
        getTreeLayout(tree, curr.left);
        getTreeLayout(tree, curr.right);
        
        return;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 34.1 MB (< 100%)
    Time complexity: O(n + m) since we go through all n and m nodes in each tree once.
    Space complexity: O(n + m) since our list will hold at most all n and m nodes' values.
*/