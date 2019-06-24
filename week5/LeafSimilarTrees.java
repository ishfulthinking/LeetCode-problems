/* 872. Leaf-Similar Trees (Easy) */

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
    public boolean leafSimilar(TreeNode root1, TreeNode root2)
    {
        List<Integer> leafSequence1 = new ArrayList<>();
        List<Integer> leafSequence2 = new ArrayList<>();
        
        getLeafSequence(leafSequence1, root1);
        getLeafSequence(leafSequence2, root2);
        
        if (leafSequence1.size() != leafSequence2.size())
            return false;
        
        // Go through each element in each list and check that they're identical.
        for (int i = 0, listSize = leafSequence1.size(); i < listSize; i++)
        {
            if (leafSequence1.get(i) != leafSequence2.get(i))
                return false;
        }
        
        return true;
    }
    
    private void getLeafSequence(List<Integer> sequence, TreeNode curr)
    {
        if (curr == null)
            return;
        
        // If the curr node has no children, add it to the sequence.
        if (curr.left == null && curr.right == null)
        {
            sequence.add(curr.val);
            return;
        }
        // Otherwise, check children to continue leaf sequencing.
        getLeafSequence(sequence, curr.left);
        getLeafSequence(sequence, curr.right);
        
        return;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 34.7 MB (< 100%)
    Time complexity: O(n + m) as we go through every node in each of the two trees only once.
    Space complexity: O(n + m) since our list could contain all n and m nodes of each tree at most.
*/