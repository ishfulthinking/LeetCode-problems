class Solution
{
    public int scoreOfParentheses(String S)
    {
        // We don't have to use a stack to solve this problem; we can get the score using the number of
        // completed pairs '()' and use a multiplier based on how many paretheses deep each pair is.
        int score = 0;
        int multiplier = -1;
        int strLen = S.length();
        
        for (int index = 0; index < strLen; index++)
        {
            // If we have an opening parenthesis, we increase our multiplier, e.g. (()) multiplies () by 2.
            if (S.charAt(index) == '(')
            {
                multiplier++;
            }
            // If it's a closing parenthesis, it's worth 1 point before we apply the multipliers.
            else
            {
                // If we JUST opened this parenthesis pairing, we can safely add it points to the score.
                if (S.charAt(index - 1) == '(')
                    score += Math.pow(2, multiplier);
                
                // No matter what, a closing parenthesis decreases the multiplier.
                multiplier--;
            }
        }
        
        return score;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 34 MB (< 99.88%)
    Time complexity: O(n) as we go through all n characters in S.
    Space complexity: O(1) since our variables are constant regardless of S.length().
*/