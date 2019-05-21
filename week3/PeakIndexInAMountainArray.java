class Solution
{
    public int peakIndexInMountainArray(int[] A)
    {
        // Assume the peak is the left edge of the mountain.
        int mountainPeak = 0;
        
        // Check the mountain range for a time when the next peak is shorter or just as tall as the previously set peak.
        // If it is, we know we've already found our peak!
        while (mountainPeak < A.length - 1)
        {
            if (A[mountainPeak + 1] <= A[mountainPeak])
            {
                break;
            }
            
            mountainPeak++;
        }
        
        return mountainPeak;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 38.3 MB (< 98.72%)
    Time complexity: O(n) since the worst case is that the peak is the very last cell in A[].
    Space complexity: O(1) since we only use an int, regardless of A[].length.
*/