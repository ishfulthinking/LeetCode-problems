class Solution
{
    public List<Integer> findAnagrams(String s, String p)
    {
        int sLen = s.length(), pLen = p.length();
        // If our desired anagram is longer than the string, it's impossible to make.
        if (pLen > sLen)
            return new ArrayList<Integer>();
        
        List<Integer> result = new ArrayList<>();
        
        // Make frequency tables for all the chars in s and p.
        int[] pFreq = new int[128];
        int[] sFreq = new int[128];
        // Mark the frequency of p
        for (int index = 0; index < pLen; index++)
        {
            pFreq[p.charAt(index)]++;
        }
        // Mark the frequencies for the first pLen chars in s.
        for (int index = 0; index < pLen; index++)
        {
            sFreq[s.charAt(index)]++;
        }
        
        // Then slide our window by decrementing the first letter and incrementing the next letter.
        for (int index = 0; index <= sLen - pLen; index++)
        {
            // Check if the current sFreq is equal to pFreq, which would imply they're anagrams.
            if (Arrays.equals(sFreq, pFreq))
                result.add(index);
            
            // Slide our window of searching by decrementing the char at index and incrementing the next char.
            sFreq[s.charAt(index)]--;
            if (index + pLen >= sLen) break;
            sFreq[s.charAt(index + pLen)]++;
        }
        
        return result;
    }
}

/*
    Runtime: 9 ms (< 76.35%)
    Memory usage: 37.3 MB (< 94.60%)
    Time complexity: O(n) since we go over all n characters in s.
    Space complexity: O(n) since the size of the arraylist depends on s and p.
        Sidenote: Does that count? We have to create an arraylist no matter what, so... hm.
*/