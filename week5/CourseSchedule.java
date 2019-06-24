/* 207. Course Schedule (Medium) */

class Solution
{
    public boolean canFinish(int numCourses, int[][] prerequisites)
    {
        if (numCourses == 0 || prerequisites.length == 0)
            return true;
        
        // We can generate a graph from the courses and their prerequisites,
        // and use a topological sort to check for cycles in the graph.
        
        // First, count the number of prereqs that each class has.
        int[] prereqCounter = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++)
        {
            prereqCounter[prerequisites[i][0]]++;
        }
        
        // Then store any courses that have no prereqs.
        // We should store these in a queue so we can use a topological sort.
        Queue<Integer> classQueue = new LinkedList<>();
        
        for (int classNum = 0; classNum < numCourses; classNum++)
        {
            if (prereqCounter[classNum] == 0)
                classQueue.add(classNum);
        }
        
        // Mark how many of our classes have no prereqs.
        int availableClasses = classQueue.size();
        
        // Finally, go through the queue and add classes to the queue if completing the prereq opens them up.
        // Continue this until we've gone through every class (or not).
        while (!classQueue.isEmpty())
        {
            int currClass = classQueue.remove();
            
            for (int prereq = 0; prereq < prerequisites.length; prereq++)
            {
                // If the current class in the queue is a prereq for another,
                if (currClass == prerequisites[prereq][1])
                {
                    // decrement the number of prereqs that class needs,
                    prereqCounter[prerequisites[prereq][0]]--;
                    // and if we've reached all the prereqs for that class, add it to the queue.
                    if (prereqCounter[prerequisites[prereq][0]] == 0)
                    {
                        availableClasses++;
                        classQueue.add(prerequisites[prereq][0]);
                    }
                }
            }
        }
        
        // After we've gone through the queue, we should now have every course available.
        return (availableClasses == numCourses);
    }
}

/*
    Runtime: 32 ms (< 28.84%)
    Memory usage: 45.7 MB (< 65.68%)
    Time complexity: O idk lol
*/