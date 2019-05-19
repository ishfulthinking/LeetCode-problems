class Solution
{
    // Make our answer and its length available to both the solution method and its helper DFS.
    String result = "";
    int maxLength = 0;
    
    public String longestWord(String[] words)
    {
        // A trie is perfect for this problem since we're gonna build a word letter by letter from other words.
        Trie trie = new Trie();
        // Put each word in the trie. Its insert method will also mark which nodes (letters) are the end of a word from words[].
        for (String word : words)
        {
            trie.insert(word);
        }
        // Check every child of the root recursively for the longest word available.
        for (int i = 0; i < 26; i++)
        {
            if (trie.root.children[i] != null && trie.root.children[i].isWord)
            {
                dfs(trie.root.children[i], 1);
            }
        }
        
        return result;
    }
    // This helper DFS will recursively check for a longer word, ultimately returning the length of the longest word.
    private void dfs(TrieNode node, int length)
    {
        for (int i = 0; i < 26; i++)
        {
            if (node.children[i] != null && node.children[i].isWord)
            {
                dfs(node.children[i], length + 1);
            }
        }
        
        if (maxLength < length)
        {
            maxLength = length;
            result = node.word;
        }
    }
}
// Our TrieNode class will use an array of trieNodes instead of a hashmap since it's quicker/more space-efficient.
class TrieNode
{
    TrieNode children[];
    boolean isWord;
    String word;
    
    public TrieNode()
    {
        children = new TrieNode[26];
        word = "";
    }
}

class Trie
{
    TrieNode root;
    
    public Trie()
    {
        root = new TrieNode();
    }
    
    public void insert(String word)
    {
        // Create a line of TrieNodes by adding a child, swapping currNode for the child, and repeating.
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
        // Mark the last node as a final letter in a word, and include the word itself for quick retrieval in the DFS.
        currNode.word = word;
        currNode.isWord = true;
    }
}