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
    public ListNode oddEvenList(ListNode head)
    {
        if (head == null || head.next == null)
            return head;
        
        ListNode curr = head;
        ListNode lastOdd = curr;
        ListNode temp = null;
        ListNode prev = null;
        int nodeNum = 1;
        
        // We'll start the while loop below on the second node, to avoid problems due to node 1 being odd.
        prev = curr;
        curr = curr.next;
        nodeNum++;
        
        // Basically: Go through the list til we find an odd one, then "insert" it between the last odd and the first even
        // by redirecting the prev and next pointers appropriately.
        while (curr != null)
        {
            // If we find an odd node, "insert" it by:
            if (nodeNum % 2 == 1)
            {
                // Setting the current node aside,
                temp = curr;
                // pulling up the first even-numbered node as curr,
                curr = lastOdd.next;
                // setting prev's next pointer to the original node's next node,
                prev.next = temp.next;
                // setting the original node's next pointer to the first even-numbered node,
                temp.next = curr;
                // and then setting the original node as the last found odd node.
                lastOdd.next = temp;
                lastOdd = temp;
                curr = prev;
            }
            // Move on to the next node.
            prev = curr;
            if (curr != null)
                curr = curr.next;
            nodeNum++;
        }
        
        return head;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 35.6 MB (< 99.98%)
    Time complexity: O(n) since iterate through all n nodes.
    Space complexity: O(1) since we only use copies of the nodes, regardless of how many there are.
*/