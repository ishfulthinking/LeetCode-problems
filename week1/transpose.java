class Solution
{
    public int[][] transpose(int[][] A)
    {
        int[][] result = new int[A[0].length][A.length];
        
        // Go through A[][] row by row, but insert into result[][] column by column.
        for (int row = 0; row < A.length; row++)
        {
            for (int col = 0; col < A[0].length; col++)
            {
                result[col][row] = A[row][col];
            }
        }
        
        return result;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 41.1 MB (< 15.21%)
    Time complexity: O(n), since we run through the n elements of the array once.
    Space complexity: O(n), since our result array holds exactly n elements.
*/