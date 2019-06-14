class Solution
{
    public boolean canPartition(int[] nums)
    {
        int sum = 0;
        for (int num : nums)
        {
            sum += num;
        }
        // If the overall sum is odd, there's no way to split the sum into two equal parts.
        if (sum % 2 == 1)
            return false;
        
        // Begin looking leftward from the last cell in nums[] for a way
		// to add up all numbers to equal sum / 2.
        return checkPartitions(nums, nums.length - 1, 0, sum / 2);
    }
    
    private boolean checkPartitions(int[] nums, int index, int currSum, int targetSum)
    {
        if (index < 0 || currSum > targetSum || nums[index] > targetSum)
            return false;
        
        if (currSum == targetSum)
            return true;
        
        // Return the result of adding the current number to the sum or skipping it.
        return (checkPartitions(nums, index - 1, currSum + nums[index], targetSum))
            || (checkPartitions(nums, index - 1, currSum, targetSum));
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 36.4 MB (< 96.83%)
    Time complexity: O(n). The worst case is it searches every single cell to find that there's no way to form an equal subset.
    Space complexity: O(1), since we don't use any additional memory that scales with nums.length.
*/