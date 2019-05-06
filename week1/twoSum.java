class Solution
{
    public int[] twoSum(int[] nums, int target)
    {
        HashMap<Integer, Integer> valAndIndex = new HashMap<>();
        
        for (int index = 0; index < nums.length; index++)
        {
            // While iterating through each value, check if its "counterpart" is in the hashmap.
            int neededValue = target - nums[index];
            
            // If it is, return a new array consisting of that counterpart's index and the current index.
            if (valAndIndex.containsKey(neededValue))
                return new int[]{valAndIndex.get(neededValue), index};
            
            // If it's not, put the current number in the hashmap and move on.
            valAndIndex.put(nums[index], index);
        }
        
        // We need a return statement just in case.
        return new int[]{0};
    }
}

/*
    Runtime: 2 ms (< 99.46%)
    Memory usage: 38.4 MB (< 56.41%)
    Time complexity: O(n) since we iterate through n elements of the array.
    Space complexity: O(n) since the size of the hashmap grows with the size of nums[].
*/