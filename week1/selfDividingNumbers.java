class Solution
{
    public List<Integer> selfDividingNumbers(int left, int right)
    {
        List<Integer> result = new ArrayList<Integer>();
        
        // Iterate through every value within the bounds.
        for (int currNum = left; currNum <= right; currNum++)
        {
            // Check if the number is self-divisible; if so, append it to the list.
            if (isSelfDivisible(currNum))
                result.add(currNum);
        }
        
        return result;
    }
    
    private boolean isSelfDivisible(int num)
    {
        // Turn the int into an array of characters so we can get each digit without having to do multiple mods and divisions.
        char[] numAsChars = String.valueOf(num).toCharArray();
        
        // For each digit (stored as char c), check if it's 0 or if the original number does not divide by the digit cleanly.
        for (char c : numAsChars)
        {
            if (c == '0' || num % (c - '0') != 0)
                return false;
        }
        
        return true;
    }
}

/*
    Runtime: 2 ms (< 90.30%)
    Memory usage: 34 MB (< 100%)
    Time complexity: O(n) where n = the digits between left and right, inclusive. (?)
    Space complexity: O(k) where k = the number of self-dividing digits, which we store in our list.
*/