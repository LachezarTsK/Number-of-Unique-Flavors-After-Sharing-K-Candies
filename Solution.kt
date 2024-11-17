
import kotlin.math.max

class Solution {

    fun shareCandies(candies: IntArray, numberOfConsecutiveCandiesToGive: Int): Int {
        val frequencyCandies = createFrequencyCandies(candies)
        var currentNumberOfUniqueFlavorsRetainedAfterGiving = findTotalNumberOfUniqueCandies(frequencyCandies)
        if (numberOfConsecutiveCandiesToGive == 0) {
            return currentNumberOfUniqueFlavorsRetainedAfterGiving
        }

        for (i in 0..<numberOfConsecutiveCandiesToGive) {
            --frequencyCandies[candies[i]]
            if (frequencyCandies[candies[i]] == 0) {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving
            }
        }
        var maxNumberOfUniqueFlavorsRetainedAfterGiving = currentNumberOfUniqueFlavorsRetainedAfterGiving

        for (i in numberOfConsecutiveCandiesToGive..<candies.size) {
            --frequencyCandies[candies[i]]
            if (frequencyCandies[candies[i]] == 0) {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving
            }

            ++frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]]
            if (frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]] == 1) {
                ++currentNumberOfUniqueFlavorsRetainedAfterGiving
            }

            maxNumberOfUniqueFlavorsRetainedAfterGiving = max(
                maxNumberOfUniqueFlavorsRetainedAfterGiving,
                currentNumberOfUniqueFlavorsRetainedAfterGiving
            )
        }

        return maxNumberOfUniqueFlavorsRetainedAfterGiving
    }

    private fun createFrequencyCandies(candies: IntArray): IntArray {
        var maxCandyValue = 0
        for (candy in candies) {
            maxCandyValue = max(maxCandyValue, candy)
        }

        val frequencyCandies = IntArray(maxCandyValue + 1)
        for (candy in candies) {
            ++frequencyCandies[candy]
        }
        return frequencyCandies
    }

    private fun findTotalNumberOfUniqueCandies(frequencyCandies: IntArray): Int {
        var totalNumberOfUniqueCandies = 0
        for (frequency in frequencyCandies) {
            if (frequency > 0) {
                ++totalNumberOfUniqueCandies
            }
        }
        return totalNumberOfUniqueCandies
    }
}
