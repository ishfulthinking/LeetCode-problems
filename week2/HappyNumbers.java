class Solution
{
    public boolean isHappy(int n)
    {
        // Negative numbers can't be happy.
        if (n < 0) return false;
        
        HashSet<Integer> seen = new HashSet<>();
        
        // To avoid an infinite loop, add each n to the hashset and quit if we repeat an n value.
        while (!seen.contains(n))
        {
            if (n == 1) return true;
            
            seen.add(n);
            n = sumDigitsSquared(n);
        }
        
        return false;
    }
    
    private int sumDigitsSquared(int n)
    {
        int sum = 0;
        
        // We use this loop to get the square of each digit in n.
        while (n != 0)
        {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        
        return sum;
    }   
}

/*
    Runtime: 2 ms (< 51.29%)
    Memory usage: 32.5 MB (< 100%)
    Time complexity: O(k), where k is the number of possible sums of digits squared before == 1 or a loop is found
    Space complexity: O(k), since the hashset will grow to the size k mentioned above.
*/