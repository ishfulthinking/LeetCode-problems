class Solution
{
    public List<String> fizzBuzz(int n)
    {
        List<String> result = new ArrayList<>();
        
        // Iterate through each number within the bounds. Check for the dual cases first,
        // then the single cases, and if all cases fail, append the int as a string.
        // If we didn't check for the dual cases first, the if statements would "short circuit"!
        for (int currNum = 1; currNum <= n; currNum++)
        {
            if (currNum % 3 == 0 && currNum % 5 == 0)
                result.add("FizzBuzz");
            else if (currNum % 3 == 0)
                result.add("Fizz");
            else if (currNum % 5 == 0)
                result.add("Buzz");
            else
                result.add(Integer.toString(currNum));
        }
        
        return result;
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 38.4 MB (< 57.28%)
    Time complexity: O(k), since we iterate through the k elements between 1 and n.
    Space complexity: O(k), since we use only the space necessary to store k elements.
*/