class Solution
{
    public int numJewelsInStones(String J, String S)
    {
        HashSet<Character> jewelSet = new HashSet<>();
        int count = 0;
        
        // Add every jewel type in J to a hashset.
        for (int i = 0, strLen = J.length(); i < strLen; i++)
        {
            jewelSet.add(J.charAt(i));
        }
        
        // Iterate through S, checking if each stone is also a jewel.
        for (int i = 0, strLen = S.length(); i < strLen; i++)
        {
            if (jewelSet.contains(S.charAt(i)))
                count++;
        }
        
        return count;
    }
}

/*
    Runtime: 1 ms (< 98.16%)
    Memory usage: 33.7 MB (< 100%)
    Time complexity: O(n) since it iterates through each array once
    Space complexity: O(n) since the max size of the hashset depends on J's length.
*/