class MyHashSet
{
    // An array is ideal since get/set will work in O(1).
    // To save on memory, make a huge bool array, where the value represents if the int is in the array.
    // We can start with a low array size and expand only if necessary.
    boolean[] mySet;
    
    public MyHashSet()
    {
        mySet = new boolean[128];
    }
    
    public void add(int key)
    {
        // If the key is higher than the array size, we have to expand it and THEN flip the "switch" for presence.
        if (key >= mySet.length)
            expandSet(key);
        // Mark it present by setting to true.
        mySet[key] = true;
    }
    
    public void remove(int key)
    {
        // If the key is larger than the array, there's nothing to "switch" off.
        if (key >= mySet.length)
            return;
        // Otherwise, just set the presence switch back to false.
        mySet[key] = false;
    }
    
    public boolean contains(int key)
    {
        // Like above, a key higher than length means it's an instant no.
        if (key >= mySet.length)
            return false;
        
        return mySet[key];
    }
    
    private void expandSet(int highest)
    {
        // Expand the array by overwriting it with a new array of size key + 1, which is what we need at most.
        mySet = Arrays.copyOf(mySet, highest + 1);
    }
}

/*
    Runtime: 63 ms (< 63.99%)
    Memory usage: 54.7 MB (< 85.54%)
    Time complexity: O(n) since we have to create new arrays of size n whenever an expansion is needed.
    Space complexity: O(n) for the same reason above.
*/