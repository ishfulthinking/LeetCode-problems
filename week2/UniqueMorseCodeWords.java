class Solution
{
    public int uniqueMorseRepresentations(String[] words)
    {
        // Initialize an array of morse code representations of each letter.
        // This makes fetching them an O(1) operation.
        String[] morseCode = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                                          ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                                          "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        
        // Hold all the possible transformations in a hashset to prevent duplicate entries.
        HashSet<String> transformations = new HashSet<>();
        
        // Iterate through every word in the input array,
        for (String currWord : words)
        {
            StringBuilder currCode = new StringBuilder();
            
            // and build a string by appending each morse code representation of each letter.
            for (int i = 0, strLen = currWord.length(); i < strLen; i++)
            {
                currCode.append(morseCode[currWord.charAt(i) - 'a']);
            }
            
            // Add the string to the hashset.
            transformations.add(currCode.toString());
        }
        
        // Since a hashset only holds unique entries, its size = the number of unique transformations!
        return transformations.size();
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 34.5 MB (< 97.32%)
    Time complexity: O(n * k), where n is the size of words[] and k is the number of characters in each word.
    Space complexity: O(n), since the size of the hashset depends on the number of  unique strings in words[].
*/