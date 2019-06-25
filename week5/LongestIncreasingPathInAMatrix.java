class Solution
{
    public int networkDelayTime(int[][] edges, int numNodes, int source)
    {
        // Note: times[i] = (source, target, time it takes to travel)
        
        // For this problem, we can use Dijkstra's algorithm, which finds the lowest-cost path between
        // a source node and a target node. We'll put all the nodes into a map, then use a minheap to
        // order them by the cost it takes to get there. That way we always use the lowest-cost way
        // to get there for paths that diverge and then converge.
        int result = 0;
        
        // First, put the graph into a map of nodes and their adjacent nodes.
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges)
        {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            // Put the edge in the source's adjacency list, along with its cost.
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        
        // Once we've created the map representation of the graph, create a priority queue
        // that orders itself by the weights of its nodes.
        PriorityQueue<int[]> heap = new PriorityQueue<>((node1, node2) -> node1[1] - node2[1]);
        heap.add(new int[]{source, 0});
        
        HashMap<Integer, Integer> distances = new HashMap<>();
        
        // While the heap has something in it, 
        while (!heap.isEmpty())
        {
            // get the node at the front, which has the lowest cost thanks to the minheap.
            int[] nodeInfo = heap.poll();
            int node = nodeInfo[0];
            int dist = nodeInfo[1];
            
            // If we've already processed that node, just skip doing it again.
            if (distances.containsKey(node))
                continue;
            
            distances.put(node, dist);
            
            // If the node has connections, check how using the node as a stepping stone along the path
            // helps the final cost of the path for each connection.
            if (graph.containsKey(node))
            {
                for (int[] edge : graph.get(node))
                {
                    int dest = edge[0];
                    int cost = edge[1];
                    // If we haven't processed the distance to reach that node yet, put it in the queue.
                    if (!distances.containsKey(dest))
                        heap.add(new int[]{dest, dist + cost});
                }
            }
        }
        // We should've processed all the nodes now, so if we haven't, it's a failure.
        if (distances.size() != numNodes)
            return -1;
        // Otherwise, find the longest path and return it.
        for (int pathLength : distances.values())
            result = Math.max(result, pathLength);
        
        return result;
    }
}

/*
    Runtime: 56 ms (< 39.53%)
    Memory usage: 54.5 MB (< 36.27%)
    Time complexity: O(E log E) since we process all E edges, and for each, we process log E edges.
        That log E comes from the usage of the minheap, which sorts our input automatically in logarithmic time.
    Space complexity: O(N + E) since at worst, our list and map contain every node + plus all the edges.
*/