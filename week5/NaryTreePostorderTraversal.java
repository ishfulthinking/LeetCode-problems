/* 590. N-ary Tree Postorder Traversal (Easy) */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution
{
    public List<Integer> postorder(Node root)
    {
        List<Integer> result = new ArrayList<>();
        
        getNodesPostorder(result, root);
        
        return result;
    }
    
    private void getNodesPostorder(List<Integer> result, Node curr)
    {
        if (curr == null)
            return;
        
        for (Node child : curr.children)
        {
            getNodesPostorder(result, child);
        }
        
        result.add(curr.val);
        
        return;
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 48.2 MB (< 28.29%)
    Time complexity: O(n) where n = the number of nodes. We iterate through every node once.
    Space complexity: O(n) since our result list will have a length of n nodes at most.
*/