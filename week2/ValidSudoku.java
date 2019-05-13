class Solution
{
    public boolean isValidSudoku(char[][] board)
    {   
        // Check each column for valid setup.
        for (int row = 0; row < board.length; row++)
        {
            // Each column will get its own validity array.
            boolean[] validity = new boolean[9];
            
            for (int col = 0; col < board[0].length; col++)
            {
                // If it's not filled, don't worry about it.
                if (board[row][col] == '.')
                    continue;
                else
                {
                    // If it's filled and we've already encountered that number in that column, return false.
                    if (validity[board[row][col] - '1'])
                        return false;
                    // Otherwise, mark that we've seen that value in this column.
                    else
                        validity[board[row][col] - '1'] = true;
                }
            }
        }
        // Check each row for valid setup, like above.
        for (int col = 0; col < board[0].length; col++)
        {
            boolean[] validity = new boolean[9];
            
            for (int row = 0; row < board.length; row++)
            {
                if (board[row][col] == '.')
                    continue;
                else
                {
                    if (validity[board[row][col] - '1'])
                        return false;
                    else
                        validity[board[row][col] - '1'] = true;
                }
            }
        }
        // Finally, we gotta check the subsquares. So we make two outer loops so we set the row and col correctly.
        for (int squareX = 0; squareX < board.length; squareX += 3)
        {
            for (int squareY = 0; squareY < board[0].length; squareY += 3)
            {
                boolean[] validity = new boolean[9];
                
                // Each square is 3x3, so set an upper limit on both row and col at 3.
                for (int row = 0; row < 3; row++)
                {
                    for (int col = 0; col < 3; col++)
                    {
                        // As above, check that it's a number and then either mark it as seen or return false.
                        if (board[squareX + row][squareY + col] == '.')
                            continue;
                        else
                        {
                            if (validity[board[squareX + row][squareY + col] - '1'])
                                return false;
                            else
                                validity[board[squareX + row][squareY + col] - '1'] = true;
                        }
                    }
                }
            }
        }
        
        // The sudoku puzzle has survived all our tests!
        return true;
    }
}

/*
    Runtime: 2 ms (< 96.49%)
    Memory usage: 43 MB (< 90.22%)
    Time complexity: O(n^2) since we check every row for every col and every col for every row.
    Space complexity: O(1) since we use a boolean array of size 9 as our only extra memory.
*/