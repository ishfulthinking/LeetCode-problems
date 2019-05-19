class Solution
{
    public List<String> findWords(char[][] board, String[] words)
    {
        // Edge cases
        if (board.length == 0 || board[0].length == 0 || words.length == 0)
            return new ArrayList<String>();
        
        List<String> foundWords = new ArrayList<>();
        
        // Populate a hashmap full of the first letters of each string and their frequencies.
        HashMap<Character, Integer> firstLetters = new HashMap<>();
        for (String word : words)
        {
            if (firstLetters.containsKey(word.charAt(0)))
                firstLetters.put(word.charAt(0), firstLetters.get(word.charAt(0)) + 1);
            else
                firstLetters.put(word.charAt(0), 1);
        }
        
        // Now begin the search... iterate through every letter in the board unless firstLetters is empty.
        for (int row = 0; row < board.length && !firstLetters.isEmpty(); row++)
        {
            for (int col = 0; col < board[0].length && !firstLetters.isEmpty(); col++)
            {
                // Only begin a DFS if the current letter is the first in a string.
                if (firstLetters.containsKey(board[row][col]))
                {
                    // Look through every word in words...
                    for (String word : words)
                    {
                        // ... for one that starts with that letter, and isn't already in our list.
                        if (word.charAt(0) == board[row][col] && !foundWords.contains(word))
                        {
                            // Once we found the word, check if the board has it using a helper DFS function.
                            if (scourBoard(board, word, 0, row, col))
                            {
                                foundWords.add(word);
                                // Don't forget to change firstLetters. If this is the last word that starts with char, remove it.
                                if (firstLetters.get(word.charAt(0)) == 1)
                                {
                                    firstLetters.remove(word.charAt(0));
                                    break;
                                }
                                // Otherwise, decrement its frequency.
                                else
                                    firstLetters.put(word.charAt(0), firstLetters.get(word.charAt(0)) - 1);
                            }
                        }
                    }
                }
            }
        }
        
        return foundWords;
    }
    
    private boolean scourBoard(char[][] board, String word, int currIndex, int row, int col)
    {
        // We've found the target!
        if (currIndex == word.length())
            return true;
        // Are we within bounds?
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return false;
        
        char target = word.charAt(currIndex);
        
        // Are we at a wrong letter?
        if (board[row][col] != target)
            return false;
        
        // Otherwise, we have hope!
        // Mark the current index as unrepeatable.
        char temp = board[row][col];
        board[row][col] = '-';
        // Now continue the DFS.
        if (scourBoard(board, word, currIndex + 1, row + 1, col)
           || scourBoard(board, word, currIndex + 1, row - 1, col)
           || scourBoard(board, word, currIndex + 1, row, col + 1)
           || scourBoard(board, word, currIndex + 1, row, col - 1)) {
            // Undo the marking so other word searches can be done, then return true.
            board[row][col] = temp;
            return true;
        }
        // We've exhausted our options. It's a failure!
        board[row][col] = temp;
        return false;
    }
}

/*
    Runtime: 247 ms (< 25.01%)
    Memory usage: 38.5 MB (< 100%)
    Time complexity: O(n * s * k) since we check every cell in board[][], and check every string s in words[], every char of every string...
    Space complexity: O(s). Our hashset will, at most, contain every string in words[].
*/