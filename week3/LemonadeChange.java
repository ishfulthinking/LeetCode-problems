class Solution
{
    public boolean lemonadeChange(int[] bills)
    {
        // The only bills that matter for change are fives and tens.
        int fives = 0, tens = 0;
        
        for (int bill : bills)
        {
            // If the bill is a 5, we don't need to give change.
            if (bill == 5)
                fives++;
            else
            {  
                if (bill == 10)
                {
                    if (fives == 0)
                        return false;
                    fives--;
                    tens++;
                }
                // The change for a 20 is either 3 fives or 1 ten and 1 five. If we don't have either, return false.
                else if (bill == 20)
                {
                    if (fives < 3 && tens == 0)
                        return false;
                    if (fives < 1)
                        return false;
                    // Put the "1 ten, 1 five" if statement first, since we want to save as many fives as possible.
                    if (tens > 0 && fives > 0)
                    {
                        tens--;
                        fives--;
                    }
                    else
                        fives -= 3;
                }
            }
        }
        
        return true;
    }
}

/*
    Runtime: 2 ms (< 97.14%)
    Memory usage: 39.6 MB (< 97.73%)
    Time complexity: O(n) since we'll process all n bills at most.
    Space complexity: O(1) since we only use three ints, regardless of bills[].length.
*/