class Solution
{
    public List<List<Integer>> subsets(int[] nums)
    {   
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // We can think of each subset as a binary representation of every integer up to nums.length.
        // E.g. 0 is the empty set, 1 is a set with only the last element in nums[], and so on.
        int rounds = 1 << nums.length;
        
        for (int i = 0; i < rounds; i++)
        {
            List<Integer> subset = new ArrayList<Integer>();
            
            for (int j = 0; j < nums.length; j++)
            {
                if ((i & (1 << j)) > 0)
                    subset.add(nums[j]);
            }
            
            result.add(subset);
        }
        
        return result;
    }
}

/*
    Runtime: 1 ms (< 64.42%)
    Memory usage: 37.2 MB (< 99.11%)
    Time complexity: O(n log n) since, for all n elements, we iterate through every bit, which is log n iterations.
    Space complexity: O(2^n) since our result list will always contain 2^n entries.
*/