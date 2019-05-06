class Solution
{
    public String reverseVowels(String s)
    {
        // The strategy here is: turn the string into an array, then scan the array from the left and
        // and right, swapping vowels in place. End the loop when the two indices cross.
        char[] strArray = s.toCharArray();
        int leftIndex = 0;
        int rightIndex = s.length() - 1;
        char temp;
        
        // Scan through the array-ified string from the left index.
        while (leftIndex < rightIndex)
        {
            // If we hit a vowel with our left index, start moving the right index.
            if (isVowel(strArray[leftIndex]))
            {
                // Move the right index leftward along the array until we hit a vowel.
                while (!isVowel(strArray[rightIndex]))
                    rightIndex--;
                
                // Swap the left index's vowel with the right's.
                temp = strArray[leftIndex];
                strArray[leftIndex] = strArray[rightIndex];
                strArray[rightIndex] = temp;
                
                // To avoid repeating the swap (and getting stuck), move the right index one space to the left.
                rightIndex--;
            }
            
            // The left index has to move regardless.
            leftIndex++;
        }
        
        return String.valueOf(strArray);
    }
    
    private boolean isVowel(char in)
    {
        if (in == 'a' || in == 'e' || in == 'i' || in == 'o' || in == 'u')
            return true;
        if (in == 'A' || in == 'E' || in == 'I' || in == 'O' || in == 'U')
            return true;
        
        return false;
    }
}

/*
    Runtime: 2 ms (< 98.72%)
    Memory usage: 39.4 MB (< 44.41%)
    Time complexity: O(n) since we go through n characters to array-ify the string and to swap vowels.
    Space complexity: O(n) since our array is the size of the input string.
*/