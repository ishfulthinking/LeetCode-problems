class Solution
{
    public boolean wordPattern(String pattern, String str)
    {
        // Note: This is just like Isomorphic Strings, but with a char-String mapping instead of a char-char one.
        
        HashMap<Character, String> patternMap = new HashMap<>();
        int startIndex = 0, stopIndex = 0;
        int patLen = pattern.length(), strLen = str.length();
        
        for (int patIndex = 0; patIndex < patLen; patIndex++)
        {
            char currChar = pattern.charAt(patIndex);
            
            // Get the current word as a substring of the bigger str.
            startIndex = stopIndex;
            // If we're starting out of bounds, there aren't enough words for each char, and there's no way it's mapped correctly.
            if (startIndex >= strLen)
                return false;
            while (stopIndex < strLen && str.charAt(stopIndex) != ' ')
                stopIndex++;
            String currWord = str.substring(startIndex, stopIndex++);
            
            // Note that we have to use equals() below to check if the two strings are equal by content, rather than id (==).

            // Does the map already have this char, and if so, does it map to currword correctly?
            if (patternMap.containsKey(currChar) && !patternMap.get(currChar).equals(currWord))
                return false;
            // Does the map already have currword mapped, but for a different char?
            if (patternMap.containsValue(currWord) &&
                    (patternMap.get(currChar) == null || !patternMap.get(currChar).equals(currWord)))
                return false;
            
            // If it passes both tests, make a new mapping.
            patternMap.put(currChar, currWord);
        }
        
        // If we haven't exhausted str, there are too many strings for each char, so it's not mapped correctly.
        if (stopIndex < strLen)
            return false;
        
        return true;
    }
}

/*
    Runtime: 1 ms (< 98.62%)
    Memory usage: 33.1 MB (< 50.67%)
    Time complexity: O(n), where n = the number of characters in str, since we go through all of them.
    Space complexity: O(n). Our hashmap could hold n items in the worst case.
*/