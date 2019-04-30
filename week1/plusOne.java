class Solution
{
    public int[] plusOne(int[] digits) 
    {
        // With this problem, we have to watch out for the edge case of an array of all 9's,
        // which would require 1 more digit than digits[]'s length (e.g. 999 -> 1000 after adding 1).
        // So we'll hold off on setting result[]'s size until after we've checked the array for all 9's.
        boolean allNines = isAllNines(digits);
        int[] result = new int[digits.length + (allNines ? 1 : 0)];
        
        // Add 1 to the rightmost digit, check if it now equals 10 and requires another carry, and move left.
        boolean carry = true;
        
        for (int currDigit = digits.length - 1; currDigit >= 0; currDigit--)
        {
            result[currDigit] = digits[currDigit] + (carry ? 1 : 0);
            
            if (result[currDigit] == 10)
            {
                result[currDigit] = 0;
                carry = true;
            }
            else
                carry = false;
        }
        // If we have all nines, there'll still be a carry. Make sure to added it to the leftmost cell!
        if (carry)
            result[0] += 1;
        
        return result;
    }
    
    // Helper function that checks if every cell contains 9.
    private boolean isAllNines(int[] num)
    {
        for (int digit : num)
            if (digit != 9)
                return false;
        
        return true;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 37.4 MB (< 42.13%)
    Time complexity: O(n), since we run through n elements a constant number of times.
    Space complexity: O(n), since we only store the required n values in result[].
*/