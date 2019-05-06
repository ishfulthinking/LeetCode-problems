/*
    I used multiple approaches to this problem to find the most time-efficient one. Scroll down to see the others!
*/



/*
    Solution 3 strategy: Use left and right indices to scan the string in place and check for matches in letters.
    This is the most time-efficient solution I found but it has lackluster memory usage.

    I also tried using s.toCharArray() to see if array-ifying the string first would make the algorithm more efficient, but it didn't.
*/
class Solution
{
    public boolean isPalindrome(String s)
    {
        int sLength = s.length();
        int leftIndex = 0;
        int rightIndex = sLength - 1;
        
        while (leftIndex < (sLength / 2) && rightIndex >= (sLength / 2))
        {
            // Move the left index rightward until we hit an alphanum char.
            while (leftIndex < (sLength / 2) && !isAlphaNum(s.charAt(leftIndex)))
                leftIndex++;
            // Similarly, move the right index leftward until we hit an alphanum char.
            while (rightIndex >= (sLength / 2) && !isAlphaNum(s.charAt(rightIndex)))
                rightIndex--;
            
            // We could have move our indices past their accepted points if there were only non-alphanum chars in the middle.
            // We return true because to have gotten to this point, the parts outside the middle must have been palindromes.
            if (leftIndex >= (sLength / 2) || rightIndex < (sLength / 2))
                return true;
            // Otherwise, check that the two alphanums match and return false if they don't.
            if (makeLowercase(s.charAt(leftIndex)) != makeLowercase(s.charAt(rightIndex)))
                return false;
            
            leftIndex++;
            rightIndex--;
        }
        
        return true;
    }
    
    // ----- Helper methods -----
    
    private boolean isAlphaNum(char in)
    {
        // I made it check for lowercase letters first since those would be most common, then capitals, and then numbers.
        // By doing it in this order, the if statement will "short-circuit" and save some time in doing calculations.
        if ((in >= 'a' && in <= 'z') || (in >= 'A' && in <= 'Z') || (in >= '0' && in <= '9'))
            return true;
        
        return false;
    }
    
    private char makeLowercase(char in)
    {
        // Check that it's a capital letter first, and if so, make it lowercase by adding the gap between 'A' and 'a'.
        if (in >= 'A' && in <= 'Z')
            return (char) (in + ('a' - 'A'));
        // Otherwise, it doesn't need to change case, so just return the original character.
        return in;
    }
}

/*
    Runtime: 2 MS (< 99.38%)
    Memory Usage: 38.4 MB (< 29.98%)
    Time complexity: O(n), since it goes through the string of size n once.
    Space complexity: O(1), since it always uses 3 integers and doesn't store anything that scales with the input.
*/





/*
    Solution 2 strategy: count the number of alphanum chars in the string, create an array of that size,
    put the alphanums in the array, and use left and right indices to check for matches.
    This has very high time efficiency but low memory efficiency.
*/
class Solution
{
    public boolean isPalindrome(String s)
    {
        int sLength = s.length();
        int letterCount = 0;
        int counter = 0;
        
        // Iterate through the string once and count all alphanumeric characters.
        for (int index = 0; index < sLength; index++)
        {
            if (isAlphaNum(s.charAt(index)))
                letterCount++;
        }
        
        // Create an array of chars and iterate through s, adding all the alphanumerics to the array.
        char[] alphaNumArray = new char[letterCount];
        
        for (int index = 0; index < sLength; index++)
        {
            char currChar = s.charAt(index);
            
            // Check if it's an alphanumeric char, and if it is, try lowercase-ing it.
            if (isAlphaNum(currChar))
                alphaNumArray[counter++] = makeLowercase(currChar);
        }
        
        // Now go through the array and compare the left index with the right index letters, returning false if not the same.
        for (int index = 0; index < letterCount; index++)
        {
            if (alphaNumArray[index] != alphaNumArray[letterCount - 1 - index])
                return false;
        }
        
        return true;
    }
    
    // ----- Helper methods -----
    
    private boolean isAlphaNum(char in)
    {
        if ((in >= 'a' && in <= 'z') || (in >= 'A' && in <= 'Z') || (in >= '0' && in <= '9'))
            return true;
        
        return false;
    }
    
    private char makeLowercase(char in)
    {
        // Check that it's a capital letter first, and if so, make it lowercase by adding the gap between 'A' and 'a'.
        if (in >= 'A' && in <= 'Z')
            return (char) (in + ('a' - 'A'));
        // Otherwise, it doesn't need to change case, so just return the original character.
        return in;
    }
}
/*
    Runtime: 3 MS (< 96.22%)
    Memory usage: 39.1 MB (< 22.58%)
*/





/*
    Solution 1 strategy: Create an arraylist of the alphanumeric characters in the string,
    then check the left and right sides of the list for the same characters.
    This solution is the slowest but has better space complexity.
*/
class Solution
{
    public boolean isPalindrome(String s)
    {
        int sLength = s.length();
        List<Character> letterList = new ArrayList<>();
        
        // Iterate through the string once and add all the letters to a list.
        for (int index = 0; index < sLength; index++)
        {
            char currChar = s.charAt(index);
            // If the char is an alphanumeric character, make it lowercase first (if a letter) and then add it to the list.
            if (isAlphaNum(currChar))
                letterList.add( currChar >= 'A' ? makeLowercase(currChar) : currChar );
        }
        
        // Go through the list with two indices (one at the start, one at the end) converging at the center.
        // Compare the characters at the indices and if they're different, immediately return false.
        int listSize = letterList.size();
        
        for (int index = 0; index < listSize / 2; index++)
        {
            if (letterList.get(index) != letterList.get(listSize - 1 - index))
                return false;
        }
        
        return true;
    }
    
    /* ----- Helper methods ----- */
    
    private boolean isAlphaNum(char in)
    {
        if ((in >= '0' && in <= '9') || (in >= 'A' && in <= 'Z') || (in >= 'a' && in <= 'z'))
            return true;
        
        return false;
    }
    
    private char makeLowercase(char in)
    {
        if (in > 'Z')
            return in;
        
        return (char) (in + ('a' - 'A'));
    }
}

/*
    Runtime: 5 MS (< 60.02%)
    Memory Usage: 37.7 MB (< 45.68%)
*/