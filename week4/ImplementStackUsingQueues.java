class MyStack
{
    Queue<Integer> stackQueue;
    Queue<Integer> temp;
    int stackSize;
    
    /** Initialize your data structure here. */
    public MyStack()
    {
        stackQueue = new LinkedList<>();
        temp       = new LinkedList<>();
        stackSize  = 0;
    }
    
    /** Push element x onto stack. */
    public void push(int x)
    {
        stackQueue.add(x);
        stackSize++;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop()
    {
        return popOrTop(true);
    }
    
    /** Get the top element. */
    public int top()
    {
        return popOrTop(false);
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty()
    {
        return (stackQueue.peek() == null);
    }
    
    // This combines pop() and top() since they're the same beyond removing the top element.
    private int popOrTop(boolean removeTop)
    {
        // First, dump all but the last entry in stackQueue into temp.
        for (int i = 0; i < stackSize - 1; i++)
        {
            temp.add(stackQueue.poll());
        }
        // Save the answer, which is the last entry in the queue.
        int answer = stackQueue.poll();
        stackSize--;
        // If it's only a top(), add the (removed) last entry into temp so it can be added back to stackQueue.
        if (!removeTop)
        {
            temp.add(answer);
            stackSize++;
        }
        // Fill the stackQueue by repeating the process backwards.
        fillStackQueue();
        
        return answer;
    }
    
    private void fillStackQueue()
    {
        // Note: This also empties temp for future use.
        for (int counter = 0; counter < stackSize; counter++)
        {
            stackQueue.add(temp.poll());
        }
    }
}

/*
    Runtime: 42 ms (< 96.70%)
    Memory usage: 34.1 MB (< 94.75%)
    Time complexity: O(n) since we, at worst, iterate through all n entries multiple times.
    Space complexity: O(n) since our stackQueue could contain all n entries at worst.
*/