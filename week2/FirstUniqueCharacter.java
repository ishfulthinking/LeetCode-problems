class Solution
{
    public int firstUniqChar(String s)
    {
        int[] firstSighting = new int[26];
        // Set lowestIndex to the highest possible value so it can grab the lowest index value later on.
        int lowestIndex = Integer.MAX_VALUE;
        
        for (int sIndex = 0, strLen = s.length(); sIndex < strLen; sIndex++)
        {
            int charIndex = s.charAt(sIndex) - 'a';
            
            // If the current letter hasn't been seen yet,
            if (firstSighting[charIndex] == 0)
            {
                // mark its first appearance.
                firstSighting[charIndex] = sIndex + 1;
                continue;
            }
            // Otherwise, mark it as no good.
            firstSighting[charIndex] = -1;
        }
        // Now iterate through our firstSighting array and choose the lowest index that's not marked as a repeat.
        for (int index = 0; index < firstSighting.length; index++)
        {
            if (firstSighting[index] > 0 && firstSighting[index] < lowestIndex)
                lowestIndex = firstSighting[index];
        }
        // Return -1 if lowestIndex never changed, and the real index if it did.
        return (lowestIndex == Integer.MAX_VALUE ? -1 : lowestIndex - 1);
    }
}

/*
    Runtime: 9 ms (< 86.55%)
    Memory usage: 37.2 MB (< 44.94%)
    Time complexity: O(n) as we iterate through all chars in s.
    Space complexity: O(1) since we always use a char array, regardless of n's value.
*/