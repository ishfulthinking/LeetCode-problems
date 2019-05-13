class Solution
{
    public int[] findErrorNums(int[] nums)
    {
        // I'm pretty sure the person who made this problem made nums[] start at 1 to make things more annoying.
        boolean[] seen = new boolean[nums.length];
        int seenTwice = 0, replaced = 0;
        
        // Iterate through all the ints in nums[] to find the double.
        for (int num : nums)
        {
            // We have to make up for the discrepancy between num and its index in seen[]. Subtract 1.
            int numIndex = num - 1;
            if (seen[numIndex])
                seenTwice = num;
            seen[numIndex] = true;
        }
        // Find the replaced number by finding the only false value in seen[]. Break when it's found.
        for (int index = 0; index < seen.length; index++)
        {
            if (!seen[index])
            {
                replaced = index + 1;
                break;
            }
        }
        
        return new int[]{seenTwice, replaced};
    }
}

/*
    Runtime: 2 ms (< 99.51%)
    Memory usage: 39.7 MB (< 97.01%)
    Time complexity: O(n). We go through all n values in nums and seen[].
    Space complexity: O(n) since the size of our boolean array depends on the size of nums.
*/