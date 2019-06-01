class Solution
{
    public double myPow(double x, int n)
    {
        // We'll use the fast powering algorithm to optimize our solution.
        
        // First we make n a long to deal with values equal to Integer.MAX_VALUE or Integer.MIN_VALUE.
        long longN = n;
        // If we have a negative exponent n, we can invert x and make n positive.
        if (longN < 0)
        {
            x = 1 / x;
            longN = -longN;
        }
        
        double product = 1;
        double currProd = x;
        
        // The method is this: for every bit in n, if it's a 1, multiply the product by x,
        // and then square x, regardless of the bit's value.
        for (long i = longN; i > 0; i /= 2)
        {
            if ((i % 2) == 1)
                product *= currProd;
            
            currProd *= currProd;
        }
        
        return product;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 32.7 MB (< 60.68%)
    Time complexity: O(log n) since we iterate through n's bits.
    Space complexity: O(1) as our memory usage does not change regardless of n.
*/