class Solution
{
    public boolean isPowerOfTwo(int n)
    {
        // We can start with 1 and shift it a bit to the left to multiply by 2.
        // If we repeat this 30 times, we've exhausted all possible powers of 2 that are integers.
        for (int pow = 0; pow < 31; pow++)
            if (n == 1 << pow)
                return true;
        
        return false;
    }
}

/*
    Runtime: 1 ms (< 92.85%)
    Memory usage: 32.3 MB (< 100%)
    Time complexity: O(1) since it will run a constant number of times in the worst case.
    Space complexity: O(1) since we're only using an integer and manipulating its bits.
*/