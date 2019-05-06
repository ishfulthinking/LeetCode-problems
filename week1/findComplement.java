class Solution
{
    public int findComplement(int num)
    {
        // 1 is the lowest value with all bits set to 1.
        int allOnes = 1;
        
        // While the current value with bits all set to 1 is less than num,
        // it must have fewer bits than num, so we need to increase it.
        while (allOnes < num)
        {
            // Shift all the bits to the left once.
            allOnes = allOnes << 1;
            // Add 1 so that the final bit is set to 1.
            allOnes++;
        }
        
        // Return the number XORed with all 1's, which will flip all the bits.
        return (num ^ allOnes);
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 32 MB (< 100%)
    Time complexity: O(1) since in the worst case, we multiply allOnes by 2 until Integer.MAX_VALUE.
    Space complexity: O(1) since we only use an int for memory, regardless of the input num.
*/