class Solution
{
    public boolean isValid(String s)
    {
        Stack<Character> parentheses = new Stack<>();
        
        for (char symbol : s.toCharArray())
        {
            // If it's an opening parenthesis, push it onto the stack.
            if (isOpener(symbol))
                parentheses.push(symbol);
            // Otherwise, check if it's the proper closing parenthesis.
            else
            {
                // If the stack is empty, we instantly know it's invalid.
                if (parentheses.empty())
                    return false;
                
                // If the symbol is the right closing parenthesis, just pop the stack.
                if (isCounterpart(parentheses.peek(), symbol))
                    parentheses.pop();
                // Otherwise, it's invalid.
                else
                    return false;
            }
        }
        // After we've gone through all the symbols, if there's anything on the stack, it's a no-go.
        if (!parentheses.empty())
            return false;
        
        return true;
    }
    
    private boolean isOpener(char symbol)
    {
        if (symbol == '(')
            return true;
        if (symbol == '{')
            return true;
        if (symbol == '[')
            return true;
        
        return false;
    }
    
    private boolean isCounterpart(char opener, char closer)
    {
        if (opener == '(' && closer == ')')
            return true;
        if (opener == '{' && closer == '}')
            return true;
        if (opener == '[' && closer == ']')
            return true;
        
        return false;
    }
}

/*
    Runtime: 1 ms (< 98.81%)
    Memory usage: 34.4 MB (< 99.97%)
    Time complexity: O(n) as we go through all n characters in string s.
    Space complexity: O(n) since at worst, the input string is all opening parentheses before we return false.
*/