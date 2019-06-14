class Solution
{
    public List<List<Integer>> combine(int n, int k)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // To cut down on memory, we'll use an array instead of lists, and turn them into lists
        // when we need to add them to result.
        int[] currArray = new int[k];
        
        for (int i = 1; i <= n - k + 1; i++)
        {
            currArray[0] = i;
            // Begin recursively getting all combinations by changing the current number to pair
            // and the index to insert it in.
            getCombinations(result, currArray, n, k, i + 1, 1);
        }
        
        return result;
    }
    
    private void getCombinations(List<List<Integer>> result, int[] array, int n, int k, int num, int index)
    {
        // If the index is out of bounds, we've made a distinct combination, so add it to result and quit.
        if (index >= array.length)
        {
            result.add(arrayToList(array));
            return;
        }
        // Otherwise, we can put the current number in the current index and get all distinct combinations.
        for (int i = num; i <= n; i++)
        {
            array[index] = i;
            getCombinations(result, array, n, k, i + 1, index + 1);
        }
        
        // Once we're done, we don't need to remove ourselves since it's an array, not a list.
        return;
    }
    
    // Since Arrays.asList() returns a list of int arrays, we need to 
    private List<Integer> arrayToList(int[] array)
    {
        List<Integer> list = new ArrayList<Integer>();
        
        for (int num : array)
        {
            list.add(num);
        }
        
        return list;
    }
}

/*
    Runtime: 9 ms (< 78.54%)
    Memory usage: 39.3 MB (< 78.45%)
    Time complexity: O(n!) since we generate n! combinations in the worst case.
    Space complexity: O(n! * k) since we will hold n! lists of size k in the result list.
*/