class Solution
{
    public List<List<Integer>> permute(int[] nums)
    {
        // For this problem, we can use Heap's algorithm.
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        getPermutations(nums, result, nums.length);
        
        return result;
    }
    
    private void getPermutations(int[] nums, List<List<Integer>> result, int size)
    {
        // If we've made all the swaps or if the input array is only 1 cell large, add the current array to result.
        if (size == 1)
        {
            result.add(createListFromArray(nums));
            return;
        }
        
        for (int i = 0; i < size; i++)
        {
            // Recursively get to the smallest swap possible.
            getPermutations(nums, result, size - 1);
            
            // If is even, swap the ith and last elements.
            if (size % 2 == 0)
            {
                int temp = nums[i];
                nums[i] = nums[size - 1];
                nums[size - 1] = temp;
            }
            // If it's odd, swap the first and last element.
            else
            {
                int temp = nums[0];
                nums[0] = nums[size - 1];
                nums[size - 1] = temp;
            }
        }
        
        return;
    }
    
    // Java will return a list of primitive ints if we do Arrays.asList(), so we need to make a custom helper function.
    private List<Integer> createListFromArray(int[] array)
    {
        List<Integer> list = new ArrayList<>();
        for (int num : array)
        {
            list.add(num);
        }
        return list;
    }
}

/*
    Runtime: 1 ms (< 99.63%)
    Memory usage: 37.5 MB (< 95.96%)
    Time complexity: O(n!) since swaps are O(1) time but we make n! different arrangements of nums[].
    Space complexity: O(n!) since we store all n! arrangements of nums[] in our result list.
*/