class Solution
{
    public int calPoints(String[] ops)
    {
        Stack<Integer> scores = new Stack<>();
        int sum = 0;
        int temp = 0, temp2 = 0;
        
        for (String round : ops)
        {
            switch (round.charAt(0))
            {
                // If we get a '+', we add the last two scores on the stack and push their sum.
                case '+':   temp = scores.pop();
                            temp2 = temp + scores.peek();
                            sum += temp2;
                            scores.push(temp);
                            scores.push(temp2);
                            break;
                // A D will double the most recent round's score.
                case 'D':   temp = scores.peek() * 2;
                            sum += temp;
                            scores.push(temp);
                            break;
                // A C will clear the most recent round's score.
                case 'C':   sum -= scores.pop();
                            break;
                // Any other input is a number, so just push it onto the stack.
                default:    temp = Integer.parseInt(round);
                            sum += temp;
                            scores.push(temp);
                            break;
            }
        }
        
        return sum;
    }
}

/*
    Runtime: 2 ms (< 96.71%)
    Memory usage: 36 MB (< 99.17%)
    Time complexity: O(n) since we go through all n rounds in ops[].
    Space complexity: O(n); at worst the stack is as long as ops[].
*/