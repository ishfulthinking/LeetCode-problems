class Solution
{
    public int distributeCandies(int[] candies)
    {
        HashSet<Integer> uniqueCandies = new HashSet<>();
        
        // Go through all the candies and mark how many are unique.
        for (int candy : candies)
        {
            if (!uniqueCandies.contains(candy))
                uniqueCandies.add(candy);
            // We can instantly break out of the loop if the size of the hashset passes the size of half of candies[].
            // The reasoning is in the below comment.
            if (uniqueCandies.size() == candies.length / 2)
                break;
        }
        
        // Now, think logically: uniqueCandies.size() <= candies.length, but the sister's share <= candies.length / 2.
        // So we can return the size of the hashset if it's < candies.length / 2, or return a max of candies.length / 2.
        return (uniqueCandies.size() < candies.length / 2 ? uniqueCandies.size() : candies.length / 2);
    }
}

/*
    Runtime: 37 ms (< 87.71%)
    Memory usage: 44.2 MB (< 66.44%)
    Time complexity: O(n/2) = O(n) since we iterate through half of candies[] at most.
    Space complexity: O(n/2) = O(n) since the max size of the hashset is half the size of candies[].
*/