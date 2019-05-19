// Regexes aren't included by default.
import java.util.regex.*;

class Solution
{
    public String countOfAtoms(String formula)
    {   
        // Let's use a regular expression to parse the formula!
        // It'll be: <1 capital letter><1 or 0 lowercase letters><optional multiplier>
        // or <left parenthesis> or <right parenthesis><optional multiplier>
        Pattern fPattern = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)");
        Matcher fMatcher = fPattern.matcher(formula);
        
        // And we'll hold the element name with its count in a treemap which is held in a stack.
        // We choose a generic treemap since our output needs to be in alphabetical order, and a treemap sorts itself.
        Stack<Map<String, Integer>> fStack = new Stack<>();
        fStack.push(new TreeMap());
        
        // While the regex parser finds matches...
        while (fMatcher.find())
        {
            String match = fMatcher.group();
            
            // If we're opening up a subformula, place an empty map on the stack.
            if (match.equals("("))
            {
                fStack.push(new TreeMap());
            }
            // If we're closing out the current subformula, pop the hashmap from the top of the stack
            // so we can multiply its elements' frequencies by the right factor.
            else if (match.charAt(0) == ')')
            {
                Map<String, Integer> top = fStack.pop();
                // Check first if there's a number following the closing parenthesis.
                int factor = (match.length() > 1) ? Integer.parseInt(match.substring(1, match.length())) : 1;
                // Then multiply the frequencies correctly.
                for (String element : top.keySet())
                {
                    fStack.peek().put(element, fStack.peek().getOrDefault(element, 0) + top.get(element) * factor);
                }
            }
            // Otherwise, we've found an element, which could have a number following it.
            else
            {
                // An element is either 1 capital letter or a capital and a lowercase.
                String element = (match.length() > 1 && isLowercase(match.charAt(1))) ? match.substring(0, 2) : match.substring(0, 1);
                int factor = (element.length() == match.length()) ? 1 : Integer.parseInt(match.substring(element.length(), match.length()));
                fStack.peek().put(element, fStack.peek().getOrDefault(element, 0) + factor);
            }
        }
        
        // We've finally parsed the whole formula! So now we build a string of each frequency.
        StringBuilder result = new StringBuilder();
        for (String element : fStack.peek().keySet())
        {
            result.append(element);
            // Only append the frequency if it's more than 1.
            if (fStack.peek().get(element) > 1)
                result.append(fStack.peek().get(element));
        }
        
        return result.toString();
    }
    
    private boolean isLowercase(char in)
    {
        if (in >= 'a' && in <= 'z')
            return true;
        
        return false;
    }
}

/*
    Runtime: 10 ms (< 14.80%) (yikes!)
    Memory usage: 34.3 MB (< 100%)
    Time complexity: O(n * p) where n is the number of chars in formula and b is the number of parentheses.
        We iterate over all n chars during each regex pass, but that only happens until we run out of subformulas (in parentheses).
    Space complexity: O(n) since our stack of treemaps will only get as large as the number of elements (which could be all n chars).
*/