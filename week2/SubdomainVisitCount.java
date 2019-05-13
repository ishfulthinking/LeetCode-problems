class Solution
{
    public List<String> subdomainVisits(String[] cpdomains)
    {
        HashMap<String, Integer> domainToCountMap = new HashMap<>();
        List<String> result = new ArrayList<>();
        
        for (String domain : cpdomains)
        {
            int startIndex = 0, stopIndex = 0;
            int visits = 0;
            
            // Get the number of visits first.
            while (domain.charAt(stopIndex) != ' ')
                stopIndex++;
            visits = Integer.parseInt(domain.substring(startIndex, stopIndex));
            
            // Now get all the subdomains by splicing from the left.
            startIndex = stopIndex + 1;
            stopIndex = domain.length();
            // Move startIndex rightward until we hit a '.', cut the string, and repeat til we get to the end.
            while (startIndex < stopIndex)
            {
                String subDomain = domain.substring(startIndex, stopIndex);

                if (domainToCountMap.containsKey(subDomain))
                    domainToCountMap.put(subDomain, domainToCountMap.get(subDomain) + visits);
                else
                    domainToCountMap.put(subDomain, visits);
                
                while (startIndex < stopIndex && domain.charAt(startIndex) != '.')
                    startIndex++;
                // Bump up one more time so our next substring start point isn't the dot.
                startIndex++;
            }
        }
        
        // Now we have to format all the entries as a list. Simple string concatenation works fine!
        for (String subDomain : domainToCountMap.keySet())
            result.add(domainToCountMap.get(subDomain) + " " + subDomain);
        
        return result;
    }
}

/*
    Runtime: 8 ms (< 96.40%)
    Memory usage: 36.5 MB (< 96.60%)
    Time complexity: O(n * k), where n = cpdomains.size() and k is the number of chars in each string.
    Space complexity: O(n). The hashmap will hold n domains + a constant number of subdomains at most.
*/