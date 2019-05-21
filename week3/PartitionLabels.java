class Solution
{
    public List<Integer> partitionLabels(String S)
    {
        if (S == null || S.length() == 0)
            return new ArrayList<Integer>();
        
        List<Integer> lengthsList = new ArrayList<>();
        
        int[] letterLastSeen = new int[26];
        Arrays.fill(letterLastSeen, -1);
        
        int latestRange = 0, prevLatestRange = -1;
        int sLen = S.length();
        
        for (int index = 0; index < sLen; index++)
        {
            int letter = S.charAt(index) - 'a';
            
            // If we haven't checked S for this letter's latest appearance, find it.
            if (letterLastSeen[letter] == -1)
            {
                char currChar = S.charAt(index);
                
                // Check every char in S that comes after this one. If we see it again, mark its latest appearance.
                for (int searchIndex = index; searchIndex < sLen; searchIndex++)
                {
                    if (S.charAt(searchIndex) == currChar)
                    {
                        letterLastSeen[letter] = searchIndex;
                        
                        // Also, if the current length of the longest substring is less than this index, update it.
                        if (latestRange < searchIndex)
                        {
                            latestRange = searchIndex;
                        }
                    }
                }
            }
            // If we're at the last appearance of the char, check if we're at the end of our shortest substring.
            // If we are, add the substring length to our list.
            if (index == latestRange)
            {
                lengthsList.add(latestRange - prevLatestRange);
                prevLatestRange = latestRange;
            }
        }
        
        return lengthsList;
    }
}

/*
    Runtime: 2 ms (< 99.68%)
    Memory usage: 35.3 MB (< 97.47%)
    Time complexity: O(n), since we iterate through the list once (outer loop) and less with each subsequent iteration of the loop.
        It's similar to iterating through S multiple times, which would be linear time.
    Space complexity: O(1) since letterLastSeen[] array and other ints don't scale with S.length().
*/