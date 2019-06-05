/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 
class Solution {
    public ListNode reverseList(ListNode head)
    {
        ListNode prev = null;
        ListNode curr = head;
        
        // Go through the entire list and reverse pointers by doing this:
        while (curr != null)
        {
            // 1. Hold the current node's "next" node for later.
            ListNode temp = curr.next;
            // 2. Set the current node's "next" pointer to point to the previous node.
            curr.next = prev;
            // 3. Set the previous node to be the current node.
            prev = curr;
            // 4. Move on to the next node by setting curr to the node that was originally next.
            curr = temp;
        }
        
        return prev;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 36.5 MB (< 99.96%)
    Time complexity: O(n). We go through all n nodes in the linked list.
    Space complexity: O(1). We use 2 (non-new) ListNode objects to hold prev and curr, regardless of n.
*/