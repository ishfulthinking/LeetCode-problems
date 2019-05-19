class TrieNode
{
    boolean isEnd;
    TrieNode[] children;
    
    public TrieNode()
    {
        children = new TrieNode[26];
    }
}

class Trie
{
    TrieNode root;
    
    public Trie()
    {
        root = new TrieNode();
    }
    
    // Insert the word by adding a child node for the char, switching currNode to that node, and repeating.
    public void insert(String word)
    {
        TrieNode currNode = root;
        
        for (char currChar : word.toCharArray())
        {
            int index = currChar - 'a';
            
            if (currNode.children[index] == null)
            {
                currNode.children[index] = new TrieNode();
            }
            
            currNode = currNode.children[index];
        }
        
        currNode.isEnd = true;
    }
    
    // search() and startsWith() are identical beyond checking if the word is complete, so let's use another method.
    public boolean search(String word)
    {
        return searchForWordOrPrefix(word, true);
    }
    public boolean startsWith(String prefix)
    {
        return searchForWordOrPrefix(prefix, false);
    }
    
    private boolean searchForWordOrPrefix(String str, boolean checkComplete)
    {
        TrieNode currNode = root;
        
        for (char currChar : str.toCharArray())
        {
            int index = currChar - 'a';
            // We can throw a false as soon as we see that the currNode doesn't have the next letter as a child.
            if (currNode.children[index] == null)
                return false;
            
            currNode = currNode.children[index];
        }
        // If we insert "apple", search("app") should return false since it's not a proper word.
        return checkComplete ? currNode.isEnd : true;
    }
}

/*
    Runtime: 72 ms (< 99.96%)
    Memory usage: 50.1 MB (< 98.95%)
    Time complexity: For both insert and the search methods, it's O(n) since we'd at worst check every letter in the word.
    Space complexity: O(n) since the number of trie nodes we'd have to make would be n at worst. Each node is O(1), though, since we use an array.
*/