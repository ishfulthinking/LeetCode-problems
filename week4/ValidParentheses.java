class Solution {
    public boolean isValid(String s)
    {
        Stack<Integer> stack = new Stack<>();
        int strLen = s.length();
        
        for (int i = 0; i < strLen; i++)
        {
            // If stack is empty, we can add an opener.
            if (stack.isEmpty())
            {
                if (isOpener(s.charAt(i)))
                    stack.push(getType(s.charAt(i)));
                // But if it's not an opener, that means we have invalid entry.
                else
                    return false;
            }
            else
            {
                // If stack has stuff, allow any openers to be added.
                if (isOpener(s.charAt(i)))
                {
                    stack.push(getType(s.charAt(i)));
                }
                // If it's a closer, check the top of the stack to make sure the two match.
                else
                {
                    if (stack.peek() != getType(s.charAt(i)))
                        return false;
                    else
                        stack.pop();
                }
            }
        }
        if (!stack.isEmpty())
            return false;
        
        return true;
    }
    
    public boolean isOpener(char in)
    {
        if (in == '(' || in == '{' || in == '[')
            return true;
        else
            return false;
    }
    
    public int getType(char in)
    {
        if (in == '(' || in == ')')
            return 1;
        if (in == '{' || in == '}')
            return 2;
        if (in == '[' || in == ']')
            return 3;
        return 0;
    }   
}