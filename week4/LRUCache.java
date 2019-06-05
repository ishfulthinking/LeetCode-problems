class LRUCache
{
    class Node
    {
        int key, value;
        Node next, prev;
        
        public Node(int key, int value)
        {
            this.key = key;
            this.value = value;
        }
    }
    
    int capacity;
    Node head, tail;
    HashMap<Integer, Node> keyToNodeMap = new HashMap<>();
    
    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        
        head = new Node(-1, 0);
        tail = new Node(-1, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key)
    {
        Node target = keyToNodeMap.get(key);
        
        if (target == null)
        {
            return -1;
        }
        
        moveToTop(target);
        return target.value;
    }
    
    public void put(int key, int value)
    {
        if (!keyToNodeMap.containsKey(key))
        {
            Node newNode = new Node(key, value);
            keyToNodeMap.put(key, newNode);
            addNode(newNode);
        
            if (keyToNodeMap.size() > capacity)
            {
                deleteTail();
            }
        }
        else
        {
            Node target = keyToNodeMap.get(key);
            target.value = value;
            keyToNodeMap.put(key, target);
            
            moveToTop(target);
        }
    }
    
    private void addNode(Node target)
    {
        head.next.prev = target;
        target.next = head.next;
        head.next = target;
        target.prev = head;
    }
    
    private void moveToTop(Node target)
    {
        target.prev.next = target.next;
        target.next.prev = target.prev;
        
        addNode(target);
    }
    
    private void deleteTail()
    {
        // real tail gets assigned to temp
        Node temp = tail.prev;
        // tail precedent connects to endtail, skipping real tail
        temp.prev.next = tail;
        // connect endtail to new real tail
        tail.prev = temp.prev;
        
        keyToNodeMap.remove(temp.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */