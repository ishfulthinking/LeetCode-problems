class Solution
{
    public int[] nextGreaterElement(int[] nums1, int[] nums2)
    {
        // We can use a stack for this problem by iterating through every number in nums2,
        // checking if the current val on the stack is less, and putting its "counterpart"
        // in a map, then returning that map as an array.
        
        Stack<Integer> partnerlessNums = new Stack<>();
        HashMap<Integer, Integer> pairings = new HashMap<>();
        int[] result = new int[nums1.length];
        
        for (int num : nums2)
        {
            // If this number is greater than the current number on the stack, peel away at the
            // stack to partner it with every number that is less than it.
            while (!partnerlessNums.empty() && num > partnerlessNums.peek())
            {
                pairings.put(partnerlessNums.pop(), num);
            }
            // And put it on the stack too, of course.
            partnerlessNums.push(num);
        }
        // After going through all the numbers, any still in the stack have no partner at all.
        // So we pair them with -1 (unless they were seen earlier in nums2).
        while (!partnerlessNums.empty())
        {
            pairings.put(partnerlessNums.pop(), -1);
        }
        // Then put all the numbers' pairings in a result array.
        for (int i = 0; i < nums1.length; i++)
        {
            result[i] = pairings.get(nums1[i]);
        }
        
        return result;
    }
}

/*
    Runtime: 3 ms (< 81.59%)
    Memory usage: 37.7 MB (< 97.49%)
    Time complexity: O(n + m) since we iterate through all n elements of nums1 and all m elements of nums2.
    Space complexity: O(n + m) since our stack at worst would contain all n and m elements.
*/