class Solution
{
    public int[][] flipAndInvertImage(int[][] A)
    {
        int numRows = A.length;
        int numCols = A[0].length;
        int[][] result = new int[numRows][numCols];
        
        // We'll do both steps in one run-through of the image.
        for (int row = 0; row < numRows; row++)
        {
            for (int col = 0; col < numCols; col++)
            {
                // First, fill in each row cell starting at the end column and moving to the beginning.
                // But before setting it, do a bitwise XOR with 1 to flip the bit.
                result[row][numCols - col - 1] = (A[row][col] ^ 1);
            }
        }
        
        return result;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 42.6 MB (< 13.20%)
    Time complexity: O(n), as we run through the n cells of the array exactly once.
    Space complexity: O(n), as we only use space necessary for the array of n elements.
*/