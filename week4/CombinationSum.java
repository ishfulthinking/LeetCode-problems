class Solution
{
    public List<List<Integer>> combinationSum(int[] candidates, int target)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // Sort candidates so that we can check if a sum will pass the target as we go rightward.
        Arrays.sort(candidates);
        // Begin the backtracking! We only want to use indices that are less than the target
        // so we can avoid unnecessary calculation.
        for (int i = 0; i < candidates.length && candidates[i] <= target; i++)
        {
            getCombos(result, new ArrayList<Integer>(), candidates, target, 0, i);
        }
        
        return result;
    }
    
    private void getCombos(List<List<Integer>> result, List<Integer> currList, int[] candidates, int target, int sum, int index)
    {
        // If adding the current number to the sum makes it too high, quit.
        if (sum + candidates[index] > target)
            return;
        // Otherwise add it to the list (and the sum) and begin checking possible paths stemming from there.
        currList.add(candidates[index]);
        sum += candidates[index];
        
        // If we've hit the target, make a COPY of the currentlist (so we can't edit it later on) and add it to result.
        if (sum == target)
        {
            result.add(new ArrayList<Integer>(currList));
            currList.remove(currList.size() - 1);
            return;
        }
        // Otherwise, the new sum is too low, so begin searching for new numbers from the current index to reach the sum.
        else
        {
            for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++)
            {
                getCombos(result, currList, candidates, target, sum, i);
            }
        }
        // Remove the current element from the list so it doesn't affect parent calls.
        currList.remove(currList.size() - 1);
        
        return;
    }
}

/*
    Runtime: 2 ms (< 99.88%)
    Memory usage: 36.9 MB (< 99.87%)
    Time complexity: O(n * k) since, in the worst case, it calculates sums for all n nums in candidates[] repeatedly, per num.
    Space complexity: O(n * k) since the list could end up containing all n elements, multiple times.
*/