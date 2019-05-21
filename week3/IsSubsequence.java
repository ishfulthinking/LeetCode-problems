class Solution
{
    public boolean isSubsequence(String s, String t)
    {
        if (s.length() == 0)
            return true;
        if (t.length() == 0 || s.length() > t.length())
            return false;
        
        int sIndex = 0, tIndex = 0;
        int sLen = s.length(), tLen = t.length();
        
        // Loop through all of s's characters, checking every t character for the one we want from s.
        while (sIndex < sLen && tIndex < tLen)
        {
            char currChar = s.charAt(sIndex);
            
            while (tIndex < tLen)
            {
                if (t.charAt(tIndex++) == currChar)
                {
                    sIndex++;
                    break;
                }
            }
        }
        
        return sIndex == sLen;
    }
}

/*
    Runtime: 5 ms (< 87.94%)
    Memory usage: 48.5 MB (< 98.76%)
    Time complexity: O(n) where n = t.length() since we at worst go through all of t's chars.
    Space complexity: O(1); we only use ints regardless of s and t's lengths.
*/