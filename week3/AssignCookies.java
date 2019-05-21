class Solution
{
    public int findContentChildren(int[] g, int[] s)
    {
        if (g.length == 0 || s.length == 0)
            return 0;
        
        int currKid = 0;
        // Sort the greed indices and cookie sizes first.
        Arrays.sort(g);
        Arrays.sort(s);
        
        // Loop through all cookies and check if each satisfies the current kid; if so, move on to the next kid.
        for (int cookieSize : s)
        {
            if (currKid >= g.length)
                break;
            if (g[currKid] <= cookieSize)
                currKid++;
        }
        
        return currKid;
    }
}

/*
    Runtime: 8 ms (< 99.78%)
    Memory usage: 39 MB (< 98.89%)
    Time complexity: O(nlogn) where n = Math.max(g.length, s.length), since we sort both arrays before iterating through them.
        As a side note, if we didn't sort, it'd be Math.min(g.length, s.length) since it quits when we've finished iterating through either.
    Space complexity: O(1).
*/