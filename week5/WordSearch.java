class Solution
{
    public boolean exist(char[][] board, String word)
    {
        if (board.length == 0 || board[0].length == 0)
            return false;
        if (word.length() == 0)
            return true;
        
        int cols = board.length;
        int rows = board[0].length;
        int strLen = word.length();
        
        // Go through the entire board until we find the first letter of the target word.
        for (int col = 0; col < cols; col++)
        {
            for (int row = 0; row < rows; row++)
            {
                if (board[col][row] == word.charAt(0)) 
                    // When we find the first letter, do a DFS to find the rest.
                    if (findLetter(board, word, 0, col, row))
                        return true;
            }
        }
        
        return false;
    }
    
    private boolean findLetter(char[][] board, String word, int currIndex, int col, int row)
    {
        // Once we've passed the length of the target word, we've officially found it, so return true.
        if (currIndex >= word.length())
            return true;
        // Otherwise, check our bounds (and the current letter) to make sure it's valid.
        if (col < 0 || col >= board.length || row < 0 || row >= board[0].length)
            return false;
        if (board[col][row] != word.charAt(currIndex))
            return false;
        
        // We can assume our letter is correct at this point!
        // To continue without repeating letters, change the curr letter to a dummy
        // and start a new depth first search.
        char tempHold = board[col][row];
        board[col][row] = '.';
        
        if (findLetter(board, word, currIndex + 1, col - 1, row))
            return true;
        if (findLetter(board, word, currIndex + 1, col + 1, row))
            return true;
        if (findLetter(board, word, currIndex + 1, col, row - 1))
            return true;
        if (findLetter(board, word, currIndex + 1, col, row + 1))
            return true;
        
        // If we didn't find the word after all,
        // replace the dummy value with the original so we can continue the search.
        board[col][row] = tempHold;
        
        return false;
    }
}

/*
    Runtime: 4 ms (< 91.05%)
    Memory usage: 38.3 MB (< 99.81%)
    Time complexity: O(n) since, at worst, we check every cell on the board 4 times just to get a false.
    Space complexity: O(1) since the memory we use doesn't scale with the size of n.
*/