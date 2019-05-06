class Solution
{
    public int numIslands(char[][] grid)
    {
        if (grid.length == 0)
            return 0;
        
        int numRows = grid.length;
        int numCols = grid[0].length;
        int islands = 0;
        
        // Go through the entire grid cell by cell.
        for (int row = 0; row < numRows; row++)
        {
            for (int col = 0; col < numCols; col++)
            {
                // If we find part of an island,
                if (grid[row][col] == '1')
                {
                    // increment our island count and
                    islands++;
                    // kill that cell and all its neighbors.
                    obliterateIsland(grid, row, col);
                }
            }
        }
        
        return islands;
    }
    
    private void obliterateIsland(char[][] grid, int row, int col)
    {
        // Make sure we're in bounds and checking an intact land cell.
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != '1')
            return;
        
        // Obliterate the cell by changing it to a sea value.
        grid[row][col] = '0';
        
        // Try obliterating adjacent cells.
        obliterateIsland(grid, row + 1, col);
        obliterateIsland(grid, row - 1, col);
        obliterateIsland(grid, row, col + 1);
        obliterateIsland(grid, row, col - 1);
        
        return;
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 42.4 MB (< 5.02%)
    Time complexity: O(n*m) = O(n^2) since we go through n cells by m cells in the array.
    Space complexity: O(1) since we only use the input array and an integer count to return the result.
*/