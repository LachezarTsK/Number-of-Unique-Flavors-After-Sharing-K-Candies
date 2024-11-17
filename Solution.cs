
using System;

public class Solution
{
    public int ShareCandies(int[] candies, int numberOfConsecutiveCandiesToGive)
    {
        int[] frequencyCandies = CreateFrequencyCandies(candies);
        int currentNumberOfUniqueFlavorsRetainedAfterGiving = FindTotalNumberOfUniqueCandies(frequencyCandies);
        if (numberOfConsecutiveCandiesToGive == 0)
        {
            return currentNumberOfUniqueFlavorsRetainedAfterGiving;
        }

        for (int i = 0; i < numberOfConsecutiveCandiesToGive; ++i)
        {
            --frequencyCandies[candies[i]];
            if (frequencyCandies[candies[i]] == 0)
            {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }
        }
        int maxNumberOfUniqueFlavorsRetainedAfterGiving = currentNumberOfUniqueFlavorsRetainedAfterGiving;

        for (int i = numberOfConsecutiveCandiesToGive; i < candies.Length; ++i)
        {
            --frequencyCandies[candies[i]];
            if (frequencyCandies[candies[i]] == 0)
            {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }

            ++frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]];
            if (frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]] == 1)
            {
                ++currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }

            maxNumberOfUniqueFlavorsRetainedAfterGiving = Math.Max(
                    maxNumberOfUniqueFlavorsRetainedAfterGiving,
                    currentNumberOfUniqueFlavorsRetainedAfterGiving);
        }

        return maxNumberOfUniqueFlavorsRetainedAfterGiving;
    }

    private int[] CreateFrequencyCandies(int[] candies)
    {
        int maxCandyValue = 0;
        foreach (int candy in candies)
        {
            maxCandyValue = Math.Max(maxCandyValue, candy);
        }

        int[] frequencyCandies = new int[maxCandyValue + 1];
        foreach (int candy in candies)
        {
            ++frequencyCandies[candy];
        }
        return frequencyCandies;
    }

    private int FindTotalNumberOfUniqueCandies(int[] frequencyCandies)
    {
        int totalNumberOfUniqueCandies = 0;
        foreach (int frequency in frequencyCandies)
        {
            if (frequency > 0)
            {
                ++totalNumberOfUniqueCandies;
            }
        }
        return totalNumberOfUniqueCandies;
    }
}
