class Solution
{
    public int search(int[] nums, int target)
    {
        int left = 0, right = nums.length - 1;
        
        // By adjusting our left and right indices for wrong values, they'll eventually cross if a match isn't found.
        while (left <= right)
        {
            int halfPoint = (left + right) / 2;
            
            if (nums[halfPoint] == target)
            {
                return halfPoint;
            }
            // If the num at the halfway point is too high, move our right index to it - 1.
            else if (nums[halfPoint] > target)
            {
                right = halfPoint - 1;
            }
            // If the num at the halfway point is too low, more our left index to it + 1.
            else
            {
                left = halfPoint + 1;
            }
        }
        
        return -1;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 38.7 MB (< 98.78%)
    Time complexity: O(log n) since we cut down the size of our array to search by 2 with each iteration.
    Space complexity: O(1) since we only use ints, regardless of nums[].length.
*/