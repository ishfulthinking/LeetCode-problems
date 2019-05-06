class Solution
{
    public int findCircleNum(int[][] M)
    {
        if (M.length == 0 || M[0].length == 0) return 0;
        
        int[] visited = new int[M.length];
        int circles = 0;
        
        // For every person in the population,
        for (int row = 0; row < M.length; row++)
        {
            // if we haven't checked on their friend connections,
            if (visited[row] == 0)
            {
                // do a depth first search to find the connections and then increment circles.
                depthFirstSearch(M, visited, row);
                circles++;
            }
        }
        
        return circles;
    }
    
    private void depthFirstSearch(int[][] M, int[] visited, int row)
    {
        // The parameters mean we were handed a particular person from the population.
        // Iterate through all of their friend connections.
        for (int col = 0; col < M.length; col++)
        {
            // If they have a connection that we haven't examined,
            if (M[row][col] == 1 && visited[col] == 0)
            {
                // mark it as visited,
                visited[col] = 1;
                // and do a depth first search on that person.
                depthFirstSearch(M, visited, col);
            }
        }
    }
}

/*
    Runtime: 1 ms (< 100%)
    Memory usage: 46.4 MB (< 15.79%)
    Time complexity: O(n^2) since we iterate through all n^2 cells of the array.
    Space complexity: O(n) since we use visited[], which has size n.
*/