class Solution
{
    public void reverseString(char[] s)
    {
        // To reverse the array in place, hold a char temporarily while we
        // swap the two characters, like a sorting algorithm.
        char temp;
        
        // We only go to s.length / 2 since otherwise, we'd reverse it twice.
        for (int index = 0; index < s.length / 2; index++)
        {
            // Save the current letter,
            temp = s[index];
            // set the current index to its "opposite" in the array,
            s[index] = s[s.length - 1 - index];
            // and then set that "opposite letter" to the previously saved letter.
            s[s.length - 1 - index] = temp;
        }
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 48 MB (78.37%)
    Time complexity: O(n), since we go through n/2 characters in the array.
    Space complexity: O(1), since we use the same input array as output.
*/