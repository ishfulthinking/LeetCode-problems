/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution
{
    public ListNode reverseKGroup(ListNode head, int k)
    {
        if (head == null || head.next == null || k < 2)
            return head;
        
        ListNode curr = head;
        ListNode prev = null;
        ListNode newHead = head;
        
        // Make sure there are enough nodes to do a reversal.
        int nodeCount = 0;
        while (curr != null && nodeCount < k + 1)
        {
            curr = curr.next;
            nodeCount++;
        }
        if (curr == null && nodeCount < k)
            return head;
        else
            curr = head;
        
        // Mark the node which will become the new head of the list.
        for (int counter = k; counter > 1 && newHead != null; counter--)
        {
            newHead = newHead.next;
        }
        
        // Now we begin reversing the list in increments of k.
        while (curr != null)
        {
            // First, look ahead at the kth node away from this one, which will end up as our next node.
            ListNode kthNode = getKthNode(curr, k);
            // If the kth node is null, it means there aren't enough nodes to do a reversal, so leave the list as is.
            if (kthNode == null)
            {
                if (prev != null)
                    prev.next = curr;
                break;
            }
            // Otherwise, set the previous node to point to the node that will end up at the front after reversal.
            else
            {
                if (prev != null)
                    prev.next = kthNode;
            }
            
            // The current node will end up at the end of the list after reversal, so set it to prev,
            prev = curr;
            // and move to the node that will come next after the entire reversal.
            curr = reverseKNodes(prev, curr, k);
        }
        // If the loop ends because curr == null, we have to make sure the final node's next points to null.
        if (prev != null)
            prev.next = curr;
        
        return newHead;
    }
    
    private ListNode getKthNode(ListNode curr, int nodesLeft)
    {
        while (curr != null && nodesLeft > 1)
        {
            curr = curr.next;
            nodesLeft--;
        }
        
        return curr;
    }
    
    private ListNode reverseKNodes(ListNode prev, ListNode curr, int nodesLeft)
    {
        while (curr != null && nodesLeft > 0)
        {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
            nodesLeft--;
        }
        // This will return the node that comes next AFTER all the reversed nodee.
        return curr;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 39 MB (< 39.96%)
    Time complexity: O(n) since we iterate through all n nodes multiple times at worst.
    Space complexity: O(1) since our memory usage does not scale with the number of nodes in the list.
*/