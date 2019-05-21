class Solution
{
    public int arrayPairSum(int[] nums)
    {
        if (nums.length == 1)
            return nums[0];
        
        // We can just sort the array and then pick the first number in each pair of ints.
        Arrays.sort(nums);
        int sum = 0;
        
        for (int index = 0; index < nums.length; index += 2)
        {
            sum += nums[index];
        }
        
        return sum;
    }
}

/*
    Runtime: 14 ms (< 91.89%)
    Memory usage: 38.8 MB (< 99.47%)
    Time complexity: O(n log n). We sort the array, which takes at worst n log n time.
    Space complexity: O(1), since we only use the int sum for our answer.
*/