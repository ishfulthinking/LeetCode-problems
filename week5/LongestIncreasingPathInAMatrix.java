class Solution
{
    // To avoid 4 different if clauses, we can run a for loop of each direction to get neighbors.
    private final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int longestIncreasingPath(int[][] matrix)
    {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        
        // To avoid having the check for bounds, we can copy the existing matrix and pad all 4 sides with 0's.
        int[][] paddedMatrix = new int[matrix.length + 2][matrix[0].length + 2];
        for (int i = 0; i < matrix.length; i++)
        {
            // params: arraycopy(source, starting index in source, dest, starting index in dest, num of elements)
            System.arraycopy(matrix[i], 0, paddedMatrix[i + 1], 1, matrix[0].length);
        }
        
        // To get the outdegrees, we can go to every non-padding cell and check if it would connect to a neighbor,
        // i.e. its neighbor has a higher value than it.
        int[][] outdegree = new int[matrix.length + 2][matrix[0].length + 2];
        for (int row = 1; row <= matrix.length; row++)
        {
            for (int col = 1; col <= matrix[0].length; col++)
            {
                for (int[] direction : directions)
                {
                    if (paddedMatrix[row][col] < paddedMatrix[row + direction[0]][col + direction[1]])
                        outdegree[row][col]++;
                }
            }
        }
        
        // We'll save the accessible (i.e. outdegree of 0) nodes as 2-cell arrays of their row and col in a list.
        List<int[]> accessibleCells = new ArrayList<>();
        for (int row = 1; row < outdegree.length - 1; row++)
        {
            for (int col = 1; col < outdegree[0].length - 1; col++)
            {
                if (outdegree[row][col] == 0)
                    accessibleCells.add(new int[]{row, col});
            }
        }
        
        // Now, go through the list of accessible cells and "delete" the connection it has to its neighbor.
        // If the neighbor now has no connections, it's now accessible, so repeat until we've gotten to the
        // end of every possible path of accessible cells.
        int pathLength = 0;
        while (!accessibleCells.isEmpty())
        {
            pathLength++;
            // We can't edit the list while we iterate through it, so we have to make a new list each iteration.
            List<int[]> newlyAccessible = new ArrayList<>();
            for (int[] cell : accessibleCells)
            {   
                for (int[] direction : directions)
                {
                    int neighborRow = cell[0] + direction[0];
                    int neighborCol = cell[1] + direction[1];

                    // If the neighbor is lower than it, subtract from the outdegree.
                    if (paddedMatrix[cell[0]][cell[1]] > paddedMatrix[neighborRow][neighborCol])
                    {
                        outdegree[neighborRow][neighborCol]--;
                        // If the neighbor's outdegree is now 0, it's now accessible and we can add it to the list.
                        if (outdegree[neighborRow][neighborCol] == 0)
                        {
                            newlyAccessible.add(new int[]{neighborRow, neighborCol});
                        }
                    }    
                }
            }
            // Update the list of accessible cells.
            accessibleCells = newlyAccessible;
        }
        
        return pathLength;
    }
}