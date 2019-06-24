/* 463. Island Perimeter (Easy) */

class Solution
{
    public int islandPerimeter(int[][] grid)
    {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        
        int perimeter = 0;
        
        // We can check the grid until we find an island, then do some quick math...
        for (int row = 0; row < grid.length; row++)
        {
            for (int col = 0; col < grid[0].length; col++)
            {
                if (grid[row][col] == 1)
                {
                    // Any island square inherently has 4 units of perimeter.
                    perimeter += 4;
                    
                    // But any neighbor left of it would delete two edges, since
                    //     the neighbor's right edge would be removed, and
                    //     the current square's left edge would be removed.
                    if (row - 1 >= 0 && grid[row - 1][col] == 1)
                        perimeter -= 2;
                    // The same thing goes for any neighbor above the current island square.
                    if (col - 1 >= 0 && grid[row][col - 1] == 1)
                        perimeter -= 2;
                }
            }
        }
        
        return perimeter;
    }
}

/*
    Runtime: 6 ms (< 99.90%)
    Memory usage: 58.7 MB (< 99.41%)
    Time complexity: O(l * w) since we iterate through all l * w cells in the grid.
    Space complexity: O(1). Our only variable is perimeter, regardless of the grid's size.
*/