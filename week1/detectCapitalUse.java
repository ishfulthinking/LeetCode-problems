class Solution
{
    public boolean detectCapitalUse(String word)
    {
        // We know all the characters are letters, so we can count up all the uppercase letters and
        // find out how many lowercase letters there are by subtracting from the word length.
        int wordLength = word.length();
        int capitalLetters = 0;
        
        // Run through the word and count how many capital letters there are.
        for (int index = 0; index < wordLength; index++)
        {
            if (word.charAt(index) < 'a')
                capitalLetters += 1;
        }
        
        // Check each case: all lowercase, all uppercase, or lowercase with a beginning uppercase.
        if (capitalLetters == 0)
            return true;
        if (capitalLetters == wordLength)
            return true;
        if (word.charAt(0) < 'a' && capitalLetters == 1)
            return true;
        
        return false;
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 37 MB (< 90.12%)
    Time complexity: O(n), since we run through the word exactly once.
    Space complexity: O(1), since we don't need an array, just some ints.
*/