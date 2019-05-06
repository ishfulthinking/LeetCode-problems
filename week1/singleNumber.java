class Solution
{
    public int singleNumber(int[] nums)
    {
        // Solution: Since XORing a number with itself will yield 0, we can XOR
        // every number in the array. The final result will be the number that never repeated!
        int result = 0;
        
        for (int index = 0; index < nums.length; index++)
        {
            result ^= nums[index];
        }
        
        return result;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 41.8 MB (< 18.32%)
    Time complexity: O(n) since we go through all n elements of nums.
    Space complexity: O(1) since we store the result in an int.
*/