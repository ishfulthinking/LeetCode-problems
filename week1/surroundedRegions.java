class Solution
{
    // I'm irked that the person who submitted this problem called it "Surrounded Regions" but called the method "solve()".
    public void solve(char[][] board)
    {
        // This is similar to the "number of islands" problem. For this one, we can mark any O's
        // touching the edge with '-', then flip all O's to X's, then set all the dashes to O's again.
        
        // Edge case: we can't have non-edge islands if all the cells are edges.
        if (board.length < 3 || board[0].length < 3) return;
        
        // Check the left and right edges for islands that touch them. Obliterate them.
        for (int row = 0; row < board.length; row++)
        {
            obliterateIsland(board, row, 0);
            obliterateIsland(board, row, board[0].length - 1);
        }
        
        // Check the top and bottom edges for islands that touch them. Obliterate them.
        for (int col = 0; col < board[0].length; col++)
        {
            obliterateIsland(board, 0, col);
            obliterateIsland(board, board.length - 1, col);
        }
        
        // Finally, mark any possible islands (O's) with X's to flip them.
        // Restore the obliterated land to O's again, too.
        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                if (board[row][col] == 'O')
                    board[row][col] = 'X';
                if (board[row][col] == '-')
                    board[row][col] = 'O';
            }
        }
        
        return;
    }
    
    private void obliterateIsland(char[][] board, int row, int col)
    {
        // Check that we're within array bounds, and that we're obliterating an island cell.
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 'O')
            return;
        
        // Mark the obliterated land.
        board[row][col] = '-';
        // Obliterate any neighbors, too.
        obliterateIsland(board, row + 1, col);
        obliterateIsland(board, row - 1, col);
        obliterateIsland(board, row, col + 1);
        obliterateIsland(board, row, col - 1);
        
        return;
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 40.9 MB (< 39.76%)
    Time complexity: O(n * m) = O(n^2) since, in the worst case, all cells touch an edge and we traverse all n*m cells twice.
    Space complexity: O(1) since we edit the input array.
*/