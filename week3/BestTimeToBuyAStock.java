class Solution
{
    public int maxProfit(int[] prices)
    {
        if (prices.length == 0)
            return 0;
        
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        
        for (int price : prices)
        {
            // If the current price is lower than the one we have, mark it.
            if (price < minPrice)
            {
                minPrice = price;
            }
            // Otherwise, we have a non-minimum, so check if we can get a better profit by selling at this point.
            else if (price - minPrice > maxProfit)
            {
                maxProfit = price - minPrice;
            }
        }
        
        return maxProfit;
    }
}

/*
    Runtime: 1 ms (< 79.30%)
    Memory usage: 35.1 MB (< 99.62%)
    Time complexity: O(n), as we iterate through all n prices.
    Space complexity: O(1) since we only use 2 ints regardless of prices[].length.
*/