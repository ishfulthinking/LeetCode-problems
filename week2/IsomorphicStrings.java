/*
    I solved this problem using two different strategies: a hashmap, and double char arrays.
    Since the char arrays were faster, I placed that solution first. Scroll further for the hashmap strategy.
*/

// Double character array solution
class Solution
{
    public boolean isIsomorphic(String s, String t)
    {
        // Instead of a hashmap, use two char arrays to create a two-way "binding" between each char.
        // This has much better runtime and memory usage.
        char[] sMapArray = new char[128];
        char[] tMapArray = new char[128];
        
        for (int index = 0, strLen = s.length(); index < strLen; index++)
        {
            char sChar = s.charAt(index);
            char tChar = t.charAt(index);
            
            // If the maparray for s has a value for s's char, check that it's correct.
            if (sMapArray[sChar] != 0 && sMapArray[sChar] != tChar)
                return false;
            // If the maparray for t has a value for t's char, check that it's correct, too.
            if (tMapArray[tChar] != 0 && tMapArray[tChar] != sChar)
                return false;
                
            // Otherwise, make new mappings.
            sMapArray[sChar] = tChar;
            tMapArray[tChar] = sChar;
        }
        
        return true;
    }
}

/*
    Runtime: 3 ms (< 96.19%)
    Memory usage: 35 MB (< 99.54%)
    Time complexity: O(n). We iterate through n characters in s and t.
    Space complexity: O(1). We use two char[128] arrays no matter the size of n.
*/



// Hashmap solution
class Solution
{
    public boolean isIsomorphic(String s, String t)
    {
        HashMap<Character, Character> letterMap = new HashMap<>();
        
        // Iterate through s, creating a mapping if we haven't seen a char yet,
        // and returning false if we've seen it and t doesn't have the right mapping.
        for (int index = 0, strLen = s.length(); index < strLen; index++)
        {
            char sChar = s.charAt(index);
            char tChar = t.charAt(index);
            
            // If the lettermap has the current char as a key, check that the mapping is correct.
            if (letterMap.containsKey(sChar) && letterMap.get(sChar) != tChar)
                return false;
            // If it doesn't have the key, make sure it doesn't already have a mapping for t's character.
            if (letterMap.containsValue(tChar))
                return false;
            // Otherwise, make a new mapping.
            letterMap.put(sChar, tChar);
        }
        
        return true;
    }
}

/*
    Runtime: 13 ms (< 34.89%)
    Memory usage: 36.3 MB (< 48.38%)
    Time complexity: O(n) since we iterate through all n characters in s and t.
    Space complexity: O(n) since our hashmap could end up contain n mappings at worst.
*/