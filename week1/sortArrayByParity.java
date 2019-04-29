class Solution
{
    public int[] sortArrayByParity(int[] A)
    {
        int[] ans = new int[A.length];
        int ansIndex = 0;
        
        // Go through the array, appending all evens to ans[].
        for (int currInt : A)
            if (currInt % 2 == 0)
                ans[ansIndex++] = currInt;
        
        // Go through the array again, appending all odds to ans[].
        for (int currInt : A)
            if (currInt % 2 != 0)
                ans[ansIndex++] = currInt;
        
        return ans;
    }
}

/* Time complexity: O(n) since it iterates through the array 2n times
   Space complexity: O(n) since it creates an array of size n */