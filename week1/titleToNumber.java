class Solution
{
    public int titleToNumber(String s)
    {
        // We can think of this as a base-26 numbering system.
        // So let's iterate from right to left of the string, multiplying each digit by an additional 26.
        int multiplier = 1;
        int result = 0;
        
        for (int index = s.length() - 1; index >= 0; index--)
        {
            result += (s.charAt(index) - 'A' + 1) * multiplier;
            multiplier *= 26;
        }
        
        return result;
    }
}

/*
    Runtime: 1 ms (< 99.92%)
    Memory usage: 37.2 MB (< 8.44%)
    Time complexity: O(n), since we iterate through the array of n elements exactly once.
    Space complexity: O(1), since we'll always return a 32-bit integer.
*/