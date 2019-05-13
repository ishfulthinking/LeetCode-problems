class Solution
{
    public String frequencySort(String s)
    {
        int[] charFreq = new int[128];
        StringBuilder result = new StringBuilder();
        
        int maxFreq = 0, maxFreqIndex = 0;
        int sLen = s.length();
        
        // Get the frequency of every letter.
        for (int index = 0; index < sLen; index++) {
            charFreq[s.charAt(index)]++;
        }
        
        // While the final string hasn't been built...
        while (result.length() < sLen)
        {
            maxFreq = 0;
            maxFreqIndex = 0;
            // ...check every index in charFreq[] for the most common one...
            for (int index = 0; index < charFreq.length; index++)
            {
                
                // System.out.println("Checking freq of " + (char) index);

                if (charFreq[index] > maxFreq)
                {
                    maxFreq = charFreq[index];
                    maxFreqIndex = index;
                }
            }
            // ... and then append it to result as many times as it showed up in s.
            while (maxFreq-- > 0)
                result.append((char) maxFreqIndex);
            // Make sure to eliminate it, too!
            charFreq[maxFreqIndex] = 0;
        }
        
        return result.toString();
    }
}

/*
    Runtime: 3 ms (< 99.73%)
    Memory usage: 36.7 MB (< 99.06%)
    Time complexity: O(n) since we go through all n characters in s, and then through charFreq[] n times at most.
    Space complexity: O(n). It would be O(1) if we only used charFreq[], but we also build a string of size n.
*/