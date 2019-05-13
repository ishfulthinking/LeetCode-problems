class Solution
{
    public String[] uncommonFromSentences(String A, String B)
    {
        // Store maps of each word with its frequency, so we can avoid words that repeat.
        HashMap<String, Integer> stringAWords = new HashMap<>();
        HashMap<String, Integer> stringBWords = new HashMap<>();
        // A hashset will allow O(1) entry of all the uncommon words.
        HashSet<String> uncommonWords = new HashSet<>();
        
        // Go through string A and add all the separate words to hashmap A with frequency counts.
        for (int i = 0, strLen = A.length(); i < strLen; i++)
        {
            int strStart = i;
            
            while (i < strLen && A.charAt(i) != ' ')
                i++;
            
            String newWord = new String(A.substring(strStart, i));
            // Check if stringAWords already has the word. Increment its frequency accordingly.
            stringAWords.put(newWord, stringAWords.containsKey(newWord) ? stringAWords.get(newWord) + 1 : 1);
        }
        
        // Repeat the same with string B.
        for (int i = 0, strLen = B.length(); i < strLen; i++)
        {
            int strStart = i;
            
            while (i < strLen && B.charAt(i) != ' ')
                i++;
            
            String newWord = new String(B.substring(strStart, i));
            stringBWords.put(newWord, stringBWords.containsKey(newWord) ? stringBWords.get(newWord) + 1 : 1);
        }
        
        // Iterate through all the words (which are the keys in the map) in hashmap A.
        for (String word : stringAWords.keySet())
        {
            // Check that the word is not in the other set, and that it only occurs once in this set, before adding to uncommonWords.
            if (!stringBWords.containsKey(word) && stringAWords.get(word) == 1)
                uncommonWords.add(word);
        }
        // Repeat for hashmap B.
        for (String word : stringBWords.keySet())
        {
            if (!stringAWords.containsKey(word) && stringBWords.get(word) == 1)
                uncommonWords.add(word);
        }
        
        // Convert the hashset since our return type is a String[].
        return uncommonWords.toArray(new String[uncommonWords.size()]);
    }
}

/*
    Runtime: 2 ms (< 100%)
    Memory usage: 35.2 MB (< 88.70%)
    Time complexity: O(n * k) where n is the number of words in A and B, and k is the length of each word.
    Space complexity: O(n) where n is the number of words in A and B (worst case: every word is unique).
*/