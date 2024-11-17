
public class Solution {

    public int shareCandies(int[] candies, int numberOfConsecutiveCandiesToGive) {
        int[] frequencyCandies = createFrequencyCandies(candies);
        int currentNumberOfUniqueFlavorsRetainedAfterGiving = findTotalNumberOfUniqueCandies(frequencyCandies);
        if (numberOfConsecutiveCandiesToGive == 0) {
            return currentNumberOfUniqueFlavorsRetainedAfterGiving;
        }

        for (int i = 0; i < numberOfConsecutiveCandiesToGive; ++i) {
            --frequencyCandies[candies[i]];
            if (frequencyCandies[candies[i]] == 0) {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }
        }
        int maxNumberOfUniqueFlavorsRetainedAfterGiving = currentNumberOfUniqueFlavorsRetainedAfterGiving;

        for (int i = numberOfConsecutiveCandiesToGive; i < candies.length; ++i) {
            --frequencyCandies[candies[i]];
            if (frequencyCandies[candies[i]] == 0) {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }

            ++frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]];
            if (frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]] == 1) {
                ++currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }

            maxNumberOfUniqueFlavorsRetainedAfterGiving = Math.max(
                    maxNumberOfUniqueFlavorsRetainedAfterGiving,
                    currentNumberOfUniqueFlavorsRetainedAfterGiving);
        }

        return maxNumberOfUniqueFlavorsRetainedAfterGiving;
    }

    private int[] createFrequencyCandies(int[] candies) {
        int maxCandyValue = 0;
        for (int candy : candies) {
            maxCandyValue = Math.max(maxCandyValue, candy);
        }

        int[] frequencyCandies = new int[maxCandyValue + 1];
        for (int candy : candies) {
            ++frequencyCandies[candy];
        }
        return frequencyCandies;
    }

    private int findTotalNumberOfUniqueCandies(int[] frequencyCandies) {
        int totalNumberOfUniqueCandies = 0;
        for (int frequency : frequencyCandies) {
            if (frequency > 0) {
                ++totalNumberOfUniqueCandies;
            }
        }
        return totalNumberOfUniqueCandies;
    }
}
