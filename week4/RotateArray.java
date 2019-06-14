class Solution
{
    public void rotate(int[] nums, int k)
    {
        if (nums.length < 2 || k == 0)
            return;
        
        // If k > nums.length, k % nums.length will not make a difference in the final placement of elements.
        k = k % nums.length;
        int swaps = 0;
        
        // Hop from element to element in intervals of k, swapping values until we get back to where we started.
        // When we get back to the startPoint, we can just move down one element and repeat the process, until
        // we've swapped every element in the array.
        for (int startPoint = 0; swaps < nums.length; startPoint++)
        {
            int curr = startPoint;
            int prev = nums[startPoint];
            do
            {
                int next = (curr + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                curr = nextPoint;
                swaps++;
            } while (startPoint != curr);
        }
        
        return;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 37.4 MB (< 58.28%)
    Time complexity: O(n) since we swap every element in the array once.
    Space complexity: O(1) since our variables don't scale with nums.length.
*/