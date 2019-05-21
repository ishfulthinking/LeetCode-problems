class Solution
{
    public int[][] merge(int[][] intervals)
    {
        if (intervals.length == 0)
            return new int[0][0];
        
        // We can sort intervals[][] by starting points, take a record of the highest end point, and continue until
        // we've passed it. Then make that an interval, add it to a map of intervals, and repeat with the new start point.
        Arrays.sort(intervals, new SortByStartPoint());
        Map<Integer, Integer> intervalMap = new TreeMap<>();
        
        int start = intervals[0][0];
        int end = intervals[0][1];
            
        for (int[] interval : intervals)
        {
            // Make sure that the intervals overlap.
            if (interval[0] <= end)
            {
                // If they do, see if we can extend our end point.
                if (interval[1] > end)
                {
                    end = interval[1];
                }
            }
            // If they don't overlap, put the past interval in the map and start a new one.
            else 
            {
                intervalMap.put(start, end);
                start = interval[0];
                end = interval[1];
            }
        }
        // We have to add the last interval we worked on, too.
        intervalMap.put(start, end);
        
        // Convert the map to an array.
        int[][] intervalArray = new int[intervalMap.size()][2];
        int cell = 0;
        for (int s : intervalMap.keySet())
        {
            intervalArray[cell++][0] = s;
        }
        cell = 0;
        for (int e : intervalMap.values())
        {
            intervalArray[cell++][1] = e;
        }
        
        return intervalArray;
    }
}
// We use a custom comparator to compare the start points of each interval.
class SortByStartPoint implements Comparator<int[]>
{
    public int compare(int[] interval1, int[] interval2)
    {
        return interval1[0] - interval2[0];
    }
}

/*
    Runtime: 8 ms (< 76.78%)
    Memory usage: (< 47.47%)
    Time complexity: O(n log n) since we sort intervals[][].
    Space complexity: O(n) since our TreeMap could, at worst, contain every interval in intervals[][].
*/