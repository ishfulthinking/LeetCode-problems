class MyQueue
{
    Stack<Integer> queueStack;
    Stack<Integer> temp;
    int queueSize;
    
    /** Initialize your data structure here. */
    public MyQueue()
    {
        queueStack = new Stack<>();
        temp = new Stack<>();
        queueSize = 0;
    }
    
    /** Push element x to the back of queue. */
    public void push(int x)
    {
        queueStack.push(x);
        queueSize++;
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop()
    {
        return betterPopPeek(true);
    }
    
    /** Get the front element. */
    public int peek()
    {
        return betterPopPeek(false);
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty()
    {
        return queueStack.empty();
    }
    
    // This combines pop() and peek() since they're the same, beyond removing the final element in the queue.
    private int betterPopPeek(boolean removeTail)
    {
        // Remove all but the final entry in queueStack by storing them in temp.
        for (int i = 0; i < queueSize - 1; i++)
        {
            temp.push(queueStack.pop());
        }
        // Store the last entry as answer.
        int answer = queueStack.pop();
        queueSize--;
        // If it was only a peek(), push the answer onto temp, too, so we can have it back in the queue.
        if (!removeTail)
        {
            temp.push(answer);
            queueSize++;
        }
        // Fill up the queueStack again by reversing the process.
        fillQueueStack();
        
        return answer;
    }
    
    private void fillQueueStack()
    {
        // Note: This also empties temp for future use.
        for (int counter = 0; counter < queueSize; counter++)
        {
            queueStack.push(temp.pop());
        }
    }
}

/*
    Runtime: 43 ms (< 61.37%)
    Memory usage: 33.9 MB (< 56.03%)
    Time complexity: O(n) since at worst we hold all n input elements.
    Space complexity: O(n) since queueStack and temp could, at worst, hold all n elements together.
*/