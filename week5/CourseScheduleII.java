class Solution
{
    public int[] findOrder(int numCourses, int[][] prerequisites)
    {
        // If there are no courses to take, it's impossible.
        if (numCourses == 0)
            return new int[0];
        
        // For this task, we can use a topological sort to order the courses based on prereqs.
        int[] topoSort = new int[numCourses];
        int[] inDegrees = new int[numCourses];
        // Hold all the nodes and their adjacent nodes as a list, placed in a map.
        HashMap<Integer, List<Integer>> adjMap = new HashMap<>();
        // We'll also have a queue of courses to make sure we process them in the right order.
        Queue<Integer> courseQueue = new LinkedList<>();
        
        // First, count the number of inward edges (i.e., the indegree) each vertex has.
        for (int i = 0; i < prerequisites.length; i++)
        {
            // Edges are expressed as (0, 1) to mean you need to take 1 to take 0, for example.
            int source = prerequisites[i][1];
            int dest   = prerequisites[i][0];
            
            // Get the adjacency list for that source node, or create a new one.
            List<Integer> adjList = adjMap.getOrDefault(source, new ArrayList<Integer>());
            // Add the destination to that list to show they're connected.
            adjList.add(dest);
            // Put the updated list back into the adjacency list map.
            adjMap.put(source, adjList);
            
            inDegrees[dest]++;
        }
        // After we've created adjacency lists for every source node, look for nodes that have 0 indegree.
        // We can then add them to the courseQueue.
        for (int i = 0; i < numCourses; i++)
        {
            if (inDegrees[i] == 0)
                courseQueue.add(i);
        }
        
        // Then, process the courses by adding the "now-unlocked" courses to the queue whenever
        // we've processed their prerequisites.
        int index = 0;
        while (!courseQueue.isEmpty())
        {
            int course = courseQueue.remove();
            
            topoSort[index] = course;
            index++;
            
            if (adjMap.containsKey(course))
            {
                for (Integer connected : adjMap.get(course))
                {
                    inDegrees[connected]--;
                    
                    if (inDegrees[connected] == 0)
                        courseQueue.add(connected);
                }
            }
        }
        // If we've processed every course, the array is full and we can return it.
        if (index == numCourses)
            return topoSort;
        // Otherwise, we return an empty int array.
        return new int[0];
    }
}

/*
    Runtime: 6 ms (< 64.95%)
    Memory usage: 45.1 MB (< 92.10%)
    Time complexity: O(N) as we process every node multiple times but linearly.
    Space complexity: O(N * E), where E is the number of edges each node has. At worst, our list holds every node and
                        every edge connected to that node (which would be every single node in the graph).
*/