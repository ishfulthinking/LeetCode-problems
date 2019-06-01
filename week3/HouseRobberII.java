class Solution
{
    public int rob(int[] nums)
    {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // For this approach, we can use the same method as in House Robber 1, but do it twice:
        // one where we do every house except the last (since it's technically adjacent), and
        // one where we start on the second house and do every house after.
        int[] firstHouseFirst = new int[nums.length - 1];
        for (int house = 0; house < nums.length - 1; house++)
        {
            firstHouseFirst[house] = nums[house];
        }
        
        int[] secondHouseFirst = new int[nums.length - 1];
        for (int house = 0; house < nums.length - 1; house++)
        {
            secondHouseFirst[house] = nums[house + 1];
        }
        // Then we choose the highest profit we can get from either strategy.
        return Math.max(robHouses(firstHouseFirst), robHouses(secondHouseFirst));
    }
    
    private int robHouses(int[] houses)
    {
        // The lowest number of houses is 1, based on the edge cases at the beginning of rob().
        if (houses.length == 1)
            return houses[0];
        if (houses.length == 2)
            return Math.max(houses[0], houses[1]);
        
        // We'll start with the house that provides the highest profit.
        int[] profits = new int[houses.length];
        profits[0] = houses[0];
        profits[1] = Math.max(houses[0], houses[1]);
        
        // From there, we check if our profit is better by robbing the current house and adding it to the profit
        // we got from 2 houses ago, or if we're better off skipping it and keeping the profit from the last house.
        for (int houseNum = 2; houseNum < houses.length; houseNum++)
        {
            profits[houseNum] = Math.max(houses[houseNum] + profits[houseNum - 2], profits[houseNum - 1]);
        }
        
        return profits[houses.length - 1];
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 33.5 MB (< 100%)
    Time complexity: O(n) since we iterate through all n houses multiple times at worst.
    Space complexity: O(n) since the houses[] arrays we create are based on the size of nums[].
*/