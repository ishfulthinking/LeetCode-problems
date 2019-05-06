class Solution
{
    public String longestCommonPrefix(String[] strs)
    {
        // Handle the edge case of an empty array first.
        if (strs.length == 0)
            return "";
        
        // Let's assume the first string is the longest. This is harmless since, if it's not, its end would signify
        // the longest common prefix.
        int strLen = strs[0].length();
        
        // The outer loop will check the char at the current index of each string to see if they're all the same.
        for (int charIndex = 0; charIndex < strLen; charIndex++)
        {
            for (String currString : strs)
            {
                // If we'd be going out of bounds, or if the two letters don't match up,
                //return the substring from  0 to the current index of the current word.
                if (charIndex >= currString.length() || strs[0].charAt(charIndex) != currString.charAt(charIndex))
                    return currString.substring(0, charIndex);
            }
        }
        
        return strs[0];
    }
}

/*
    Runtime: 1 ms (< 89.74%)
    Memory usage: 38.5 MB (< 25.28%)
    Time complexity: O(n^2) since, in the worst case, we would compare all n letters with all other n letters.
    Space complexity: O(1) since we don't use any variables that scale with the input string.
*/