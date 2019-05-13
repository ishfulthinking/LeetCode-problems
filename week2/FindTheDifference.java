class Solution
{
    public char findTheDifference(String s, String t)
    {
        if (s.length() == 0) return t.charAt(0);
        // Let's use two char frequency arrays.
        int[] sFreq = new int[26];
        int[] tFreq = new int[26];
        int sLen = s.length(), tLen = t.length();
        int index;
        
        // Count up the frequency of all chars in s and t.
        for (index = 0; index < sLen; index++)
        {
            sFreq[s.charAt(index) - 'a']++;
            tFreq[t.charAt(index) - 'a']++;
        }
        // t is 1 char longer, so count that one too.
        tFreq[t.charAt(tLen - 1) - 'a']++;
        
        // Now just iterate through both arrays and as soon as a difference is found, return the char!
        for (index = 0; index < sLen; index++)
        {
            if (sFreq[index] != tFreq[index])
                break;
        }
        return (char) (index + 'a');
    }
}

/*
    Runtime: 1 ms (< 99.60%)
    Memory usage: 35.3 MB (< 94.12%)
    Time complexity: O(n) as we run through all n characters in t at most.
    Space complexity: O(1) since we always use two int arrays regardless of s or t's size.
*/