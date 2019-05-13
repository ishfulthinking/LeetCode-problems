class Solution
{
    public int numSpecialEquivGroups(String[] A)
    {
        // We can group words by the counts of each letter at a certain parity index.
        // E.g., "abc" has 1 odd a and c, and 1 even b. "apple" has 1 odd a, p, and e, while it has 1 even p and l.
        // This works perfectly because special-equivalent words in the same group will have identical letter makeups:
        // "abc" and "cba" are special equivalent, for example, and we instantly know "abcac" can't be special equivalent
        // since they have different total letter counts.
        // We can turn these int arrays into Strings to make storage easier, and so the hashset's equals() method works properly.
        
        HashSet<String> groups = new HashSet<>();
        
        for (String word : A)
        {
            // Java initializes all values in an int array to 0 upon creation.
            int[] countArray = new int[52];
            
            for (int index = 0, wordLen = word.length(); index < wordLen; index++)
            {
                // Add the letter to the array, adding 26 first if it's even parity.
                countArray[word.charAt(index) - 'a' + (index % 2 == 1 ? 0 : 26)]++;
            }
            
            // Repeated groups will be ignored since hashsets only contain unique values.
            groups.add(Arrays.toString(countArray));
        }
        
        return groups.size();
    }
}

/*
    Runtime: 11 ms (< 65.61%)
    Memory usage: 36.1 MB (< 93.59%)
    Time complexity: O(n * k) where n is the number of strings in A and k is the number of chars in each.
    Space complexity: O(n) since the hashset, at its worst, will hold all n strings in A.
*/