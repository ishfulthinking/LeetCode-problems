class Solution
{
    public int findMinArrowShots(int[][] points)
    {
        // Make sure we actually have balloons to pop.
        if (points.length == 0 || points[0].length == 0)
        {
            return 0;
        }
        
        // To solve this problem, we can look at the "end point" of any balloon that comes first,
        // then pop all balloons that start before it, and then repeat with the next end point.
        
        // We need to sort by the end points first, so let's use a custom comparator (made in a below class).
        Arrays.sort(points, new SortByEndPoint());
        
        int arrows = 1;
        // The first end point we'll look at is the balloon that has the earliest end point.
        int currEnd = points[0][1];
        
        // For every balloon, check the start and end points, and if the balloon's start is after currEnd,
        // add to arrows and switch this balloon as the balloon whose end we are looking at.
        for (int[] balloon : points)
        {
            if (currEnd < balloon[0])
            {
                arrows++;
                currEnd = balloon[1];
            }
        }
        
        return arrows;
    }
}

class SortByEndPoint implements Comparator<int[]>
{
    // A typical compare() method returns a pos value if obj1 is greater (comes later), 0 if equal, or neg if obj1 is less.
    public int compare(int[] array1, int[] array2)
    {
        return array1[1] - array2[1];
    }
}

/*
    Runtime: 20 ms (< 93.22%)
    Memory usage: 45.5 MB (< 77.01%)
    Time complexity: O(nlogn) since our sorting algorithm will be nlogn.
    Space complexity: O(1) since we don't use any extra space (we sort points[][] in place).
*/