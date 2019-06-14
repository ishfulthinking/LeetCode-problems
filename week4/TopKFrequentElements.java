class Solution
{
    public List<Integer> topKFrequent(int[] nums, int k)
    {
        // First, store every element alongside its frequency in a hashmap.
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums)
        {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        
        // Java doesn't natively support heaps, but we can use a priority queue for the same purpose.
        // So create a priority queue with a comparator that sorts by the values of each entry in the map.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((key1, key2) -> freqMap.get(key1) - freqMap.get(key2));
        
        // Then remove all entries in the priority queue that aren't the top k most frequent.
        for (int entry : freqMap.keySet())
        {
            maxHeap.add(entry);
            
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        
        // The top k most frequent elements will be the elements left in maxHeap.
        List<Integer> result = new ArrayList<Integer>();
        while (!maxHeap.isEmpty())
        {
            // Add the elements at index 0, so the most frequent entry is first after.
            result.add(0, maxHeap.poll());
        }
        
        return result;
    }
}

/*
    Runtime: 45 ms (< 24.81%)
    Memory usage: 39.4 MB (< 61.23%)
    Time complexity: O(n) since we check every element in nums, and adding to a maxheap is a log n operation.
    Space complexity: O(n) since at worst the hashmap and maxheap contain n elements.
*/