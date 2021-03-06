class Solution
{
    public boolean isAnagram(String s, String t)
    {
        // If the two strings' lengths are different, we instantly know they're not anagrams.
        if (s.length() != t.length())
            return false;
        
        // Turn each string into a char array for faster index retrieval.
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();

        // The ASCII table is 128 characters. Although it uses more memory, this cuts down processing
        // by avoiding having to subtract 'a' from every character, like we would in an array of size 26.
        int[] sCount = new int[128];
        int[] tCount = new int[128];

        // Iterate through both array-ified strings and increment the frequency of each letter in the count arrays.
        for (int index = 0; index < sArray.length; index++)
        {
            sCount[sArray[index]]++;
            tCount[tArray[index]]++;
        }
        
        // Iterate through the lowercase letters part of the count array and check for discrepancies. 
        for (int index = 'a'; index < 'z'; index++)
        {
            if (sCount[index] != tCount[index])
                return false;
        }
        
        return true;
    }
}

/*
    Runtime: 2 ms (< 99.06%)
    Memory usage: 37.5 MB (< 70.84%)
    Time complexity: O(n) since we run through each string of n characters once.
    Space complexity: O(1) since the sizes of our arrays are constant.
*/