class Solution
{
    public List<Integer> findAnagrams(String s, String p)
    {
        int sLen = s.length(), pLen = p.length();
        
        // If p is longer than s, there's no way they're anagrams.
        if (pLen > sLen)
            return new ArrayList<Integer>();
        
        List<Integer> result = new ArrayList<>();
        
        // For this approach we'll use sorting, since we used frequency tables last week.
        
        // First make a list of p and sort it, so we can use it as a sliding window.
        List<Character> pList = new ArrayList<>();
        for (int i = 0; i < pLen; i++)
        {
            pList.add(p.charAt(i));
        }
        Collections.sort(pList);
        
        // Start off with a window of s's substring, alphabetize it, then slide over by adjusting letters.
        // We don't use s.substring() repeatedly because it's too costly.
        // Copy the first pLen characters into the window, then sort it.
        List<Character> window = new ArrayList<>();
        for (int i = 0; i < pLen; i++)
        {
            window.add(s.charAt(i));
        }
        Collections.sort(window);
        // Compare the sorted window with the sorted p.
        if (window.equals(pList))
            result.add(0);
        
        // Now slide the window to the right while it's within s, and insert (in place) the "new" char each time.
        for (int right = pLen; right < sLen; right++)
        {
            window.remove(Character.valueOf(s.charAt(right - pLen)));
            
            char toInsert = s.charAt(right);
            
            // Do a binary search to insert the new character in place.
            int l = 0, r = window.size() - 1;
            int insertAt = (l + r) / 2;
            while (l <= r)
            {
                insertAt = (l + r) / 2;
                if (window.get(insertAt) < toInsert)
                {
                    l = insertAt + 1;
                    if (l > r)
                        insertAt++;
                }
                else if (window.get(insertAt) > toInsert)
                {
                    r = insertAt - 1;
                }
                else
                    break;
            }
            window.add(insertAt, toInsert);
            
            if (window.equals(pList))
                result.add(right - pLen + 1);
        }
        
        return result;
    }
}

/*
    Sorting works, but is significantly worse than solving using a char frequency array.

    Runtime: 417 ms (< 18.02%)
    Memory usage: 40.5 MB (< 27.78%)
    Time complexity: O(n log n) since we sort pList and window using regular sorting.
    Space complexity: O(n) since our anagram could be the size of string s itself.
*/