/* 785. Is Graph Bipartite? (Medium) */

class Solution
{
    public boolean isBipartite(int[][] graph)
    {
        // For a graph to be bipartite, it has to be 2-colorable.
        // Instead of ints, we can use 2 booleans to save on memory.
        boolean[] colors = new boolean[graph.length];
        boolean[] seen = new boolean[graph.length];
        
        for (int node = 0; node < graph.length; node++)
        {
            // If we haven't colored the node, check if it's valid via DFS.
            if (!seen[node] && !validDFS(graph, colors, seen, node, true))
                return false;
        }
                
        return true;
    }
                
    private boolean validDFS(int[][] graph, boolean[] colors, boolean[] seen, int node, boolean color)
    {
        // If we've seen the node, just return if its color is what it's supposed to be.
        if (seen[node])
            return (colors[node] == color);
        
        // Mark it the correct color and mark it as seen, too.
        colors[node] = color;
        seen[node] = true;
        // Then continue the DFS with its connected nodes.
        for (int connectedNode : graph[node])
        {
            if (!validDFS(graph, colors, seen, connectedNode, !color))
                return false;
        }
        // After checking its connected nodes for validity, go back and return true.
        return true;
    }
}

/*
    Runtime: 0 ms (< 100%)
    Memory usage: 44.1 MB (< 71.32%)
    Time complexity: O(n) since we check every node only once.
    Space complexity: O(n) since our two arrays scale with the size of n.
*/