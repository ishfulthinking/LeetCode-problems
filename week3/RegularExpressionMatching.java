class Solution
{
    // Store the results in an array so we can reference our previous "checks" for matching.
    boolean[][] memo;
    // A "seen" array so we don't repeat operations on the same cells.
    boolean[][] seen;
    // We want to be able to see the string and the pattern from anywhere, even outside isMatch().
    String text, pattern;
    int textLen, patternLen;
    
    public boolean isMatch(String s, String p)
    {
        textLen = s.length();
        patternLen = p.length();
        
        memo = new boolean[textLen + 1][patternLen + 1];
        seen = new boolean[textLen + 1][patternLen + 1];
        text = s;
        pattern = p;
        
        return dp(0, 0);
    }
    
    private boolean dp(int textIndex, int patternIndex)
    {
        // If we've interacted with the cell before, return its value.
        if (seen[textIndex][patternIndex])
        {
            return (memo[textIndex][patternIndex]);
        }
        
        boolean result = false;
        
        // If we're at the end of the pattern, check if we're also at the end of the text.
        if (patternIndex == patternLen)
        {
            result = (textIndex == textLen);
        }
        else
        {
            // Check if we've hit the end of the text string, and
            // if the char at the current pattern index == the one in text OR there's a period there (wildcard).
            boolean firstMatch = (textIndex < textLen && (pattern.charAt(patternIndex) == text.charAt(textIndex) ||
                                                                pattern.charAt(patternIndex) == '.'));
    
            if (patternIndex + 1 < patternLen && pattern.charAt(patternIndex + 1) == '*')
            {
                // If there's a * symbol after the current char, recursively call dp() or
                // check for a character match and advance the text symbol.
                result = ( dp(textIndex, patternIndex + 2) ||
                          ( firstMatch && dp(textIndex + 1, patternIndex) ) );
            }
            else
            {
                // If there's no * symbol, advance both the textIndex and patternIndex.
                result = firstMatch && dp(textIndex + 1, patternIndex + 1);
            }
        }
        
        memo[textIndex][patternIndex] = result;
        seen[textIndex][patternIndex] = true;
        
        return result;
    }
}

/*
    Runtime: 2 ms (< 97.73%)
    Memory usage: 35.4 MB (< 99.99%)
    Time complexity: O(s * p) where s and p are the char counts for s and p.
    Space complexity: O(s * p) since we use a 2d array with length and width of s and p.
*/