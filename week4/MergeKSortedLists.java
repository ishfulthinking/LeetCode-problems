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
    public ListNode mergeKLists(ListNode[] lists)
    {
        if (lists.length == 0)
            return null;
        
        // We'll use a PriorityQueue, which works like a heap, to organize the list nodes.
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(new NodeComparator());
        
        for (ListNode node : lists)
        {
            if (node != null)
                heap.add(node);
        }
        
        // So now we have all the of the list's HEADS (but not nodes) in the heap.
        ListNode head = new ListNode(0);
        ListNode curr = head;
        // To make the lists into one single list, we need to keep appending the first node in the heap
        // to the current node, until there's nothing left in the heap (i.e. we've chained them all into one list).
        while (!heap.isEmpty())
        {
            // Set the current node's nextnode to the front of the queue (which has the lowest value),
            curr.next = heap.poll();
            // move onto that node's nextnode,
            curr = curr.next;
            // and then if THAT nextnode isn't null, add it to the heap so it can be ordered correctly.
            if (curr.next != null)
                heap.add(curr.next);
        }
        
        return head.next;
    }
}

class NodeComparator implements Comparator<ListNode>
{
    public int compare(ListNode node1, ListNode node2)
    {
        return node1.val - node2.val;
    }
}

/*
    Runtime: 5 ms (< 81.35%)
    Memory usage: 41.9 MB (< 22.72%)
    Time complexity: O(n log m) since we iterate through every node in every list and add to the heap in batches of m (the number of lists).
    Space complexity: O(m) since our priorityqueue will hold m nodes (i.e. list heads) at most.
*/