class Solution
{
    public String mostCommonWord(String paragraph, String[] banned)
    {
        if (paragraph.length() == 0) return "";
        
        HashMap<String, Integer> wordFreq = new HashMap<>();
        HashSet<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        
        int startIndex = 0, stopIndex = 0;
        int parLen = paragraph.length();
        int highestFreq = 0;
        
        String highestFreqWord = "";
        
        // Chop up all the words, make them lowercase, and add to their frequency in the map.
        while (startIndex < parLen)
        {
            while (stopIndex < parLen && !isSeparator(paragraph.charAt(stopIndex)))
                stopIndex++;
            
            // Make the word lowercase so "ball" and "BALL" are stored the same.
            String word = paragraph.substring(startIndex, stopIndex).toLowerCase();
            // Before anything else, check that our word isn't banned.
            if (!bannedWords.contains(word))
            {
                // Then add it and its frequency to the map.
                if (wordFreq.containsKey(word))
                    wordFreq.put(word, wordFreq.get(word) + 1);
                else
                    wordFreq.put(word, 1);
            }
            
            // Now move startIndex until it's at a letter/end of the sentence.
            startIndex = stopIndex;
            while (startIndex < parLen && isSeparator(paragraph.charAt(startIndex)))
                startIndex++;
            stopIndex = startIndex;
        }
        
        // Now check every word's frequency to find the most frequent.
        for (String word : wordFreq.keySet())
        {
            if (highestFreq < wordFreq.get(word))
            {
                highestFreq = wordFreq.get(word);
                highestFreqWord = word;
            }
        }
        
        return highestFreqWord;
    }
    
    private boolean isSeparator(char in)
    {
        // On the ASCII table, punctuation symbols begin with ' ' and end at '?'.
        if (in >= ' ' && in <= '?')
            return true;
        
        return false;
    }
}

/*
    Runtime: 5 ms (< 94.63%)
    Memory usage: 35 MB (< 95.61%)
    Time complexity: O(n) as we go through all n chars in paragraph.
    Space complexity: O(n*k) since our hashmap, at worst, holds all n words made up of k letters.
*/