class Solution
{
    public int minDistance(String word1, String word2)
    {
        // For this problem, we can use a Levenshtein Distance algorithm.
        // Levenshtein Distance refers to the number of insertions, changes, or deletions
        // required to change one string to another in single-edit increments.
        
        int word1Len = word1.length();
        int word2Len = word2.length();
        // If either or both of the strings are empty, the number of steps is the length of the longer string or 0.
        if (word1Len == 0 || word2Len == 0)
            return word1Len + word2Len;
        
        // For a dynamic programming approach, we can store the edit counts in an array.
        int[][] changes = new int[word1Len + 1][word2Len + 1];
        
        // Initialize the first row's values to 0 to word1Len.
        for (int row = 0; row <= word1Len; row++)
            changes[row][0] = row;
        // Initialize the first column's values to 0 to word2Len.
        for (int col = 0; col <= word2Len; col++)
            changes[0][col] = col;
        
        // We go through every cell of the array except the first row/col, and do this:
        // 1. Check if the character in each index of string 1 is the same as the chars in string 2.
        //    If it is, add a cost of 1 to that "move".
        // 2. Propagate the costs down and to the right of the array by finding the minimums of the
        //    the current cell + cost, the cell to the left, and the to the left and up.
        for (int row = 1; row <= word1Len; row++)
        {
            char cOfStr1 = word1.charAt(row - 1);
            
            for (int col = 1; col <= word2Len; col++)
            {
                char cOfStr2 = word2.charAt(col - 1);
                int cost = 0;
                
                if (cOfStr1 != cOfStr2)
                    cost = 1;
                
                changes[row][col] = Math.min(changes[row - 1][col - 1] + cost, Math.min(changes[row - 1][col] + 1, changes[row][col - 1] + 1));
            }
        }
        
        // Whatever value is at the bottom right is the absolute minimum number of changes required!
        return changes[word1Len][word2Len];
    }
}

/*
    Runtime: 4 ms (< 93.99%)
    Memory usage: 35.3 MB (< 63.40%)
    Time complexity: O(n * m) since we compare all n chars in str1 to all m chars in str2.
    Space complexiry: O(n * m) since our array size is n * m.
*/