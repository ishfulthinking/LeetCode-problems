class Solution
{
    public int hammingDistance(int x, int y)
    {
        int distance = 0;
        
        // A bitwise XOR will highlight all different bits as 1's.
        x = x ^ y;
        
        // Then go through the bits and increment distance whenever a 1 is encountered.
        for (int i = 0; i < 32; i++)
        {
            // Use a bitwise AND to tell us if we encounter a 1.
            if ((x >> i & 1) == 1)
                distance++;
        }
        
        return distance;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 32 MB (< 100%)
    Time complexity: O(1) since in the worst case, we shift the bits 31 times.
    Space complexity: O(1) since we only use an int to hold the distance. The rest is done in place.
*/