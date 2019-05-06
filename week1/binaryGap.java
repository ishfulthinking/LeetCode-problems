class Solution
{
    public int binaryGap(int N)
    {
        int maxDistance = 0;
        int counter = 0;
        
        // While the smallest bit is 0 and N isn't equal to 0, shift N 1 bit to the right.
        while ((N & 1) == 0 && N != 0)
            N = N >> 1;
        
        // Now that we've found a 1, continue shifting bits and update maxDistance whenever we find another 1.
        // If there were less than two 1 bits, the loop will exit and return maxDistance which is still equal to 0.
        while (N != 0)
        {
            N = N >> 1;
            counter++;
            
            // When we encounter a 1 bit, update maxDistance accordingly and reset the distance counter.
            if ((N & 1) == 1)
            {
                maxDistance = Math.max(maxDistance, counter);
                counter = 0;
            }
        }
        
        return maxDistance;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 32.2 MB (< 100%)
    Time complexity: O(1) since it'll shift bits 31 times in the worst case.
    Space complexity: O(1) since it only uses 2 integers to hold the distance value and counter.
*/