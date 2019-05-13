class Solution
{
    public int[] intersection(int[] nums1, int[] nums2)
    {
        // Intersection of sets = all the items that are common to each set.
        // So let's make a hashset of things in nums1[], and another of items common between nums1[] and nums2[].
        
        HashSet<Integer> uniqueNums1 = new HashSet<>();
        HashSet<Integer> commonNums = new HashSet<>();
        
        // Add all the nums from nums1[] into a hashset.
        for (int index = 0; index < nums1.length; index++)
        {
            if (!uniqueNums1.contains(nums1[index]))
                uniqueNums1.add(nums1[index]);
        }
        // Check all of nums2's indices for numbers already in uniqueNums1.
        for (int index = 0; index < nums2.length; index++)
        {
            if (uniqueNums1.contains(nums2[index]))
                commonNums.add(nums2[index]);
        }
        // Create an int array of the commonnums by iterating over the hashset.
        int[] result = new int[commonNums.size()];
        int index = 0;
        
        for (Integer i : commonNums)
            result[index++] = i;
        
        return result;
    }
}

/*
    Runtime: 2 ms (< 97.50%)
    Memory usage: 35.9 MB (< 57.37%)
    Time complexity: O(n) since we iterate over n elements of each array.
    Space complexity: O(n) since we create hashsets and an array of, at worst, size n.
*/