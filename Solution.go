
package main

import "fmt"

func shareCandies(candies []int, numberOfConsecutiveCandiesToGive int) int {
    frequencyCandies := createFrequencyCandies(candies)
    currentNumberOfUniqueFlavorsRetainedAfterGiving := findTotalNumberOfUniqueCandies(frequencyCandies)
    if numberOfConsecutiveCandiesToGive == 0 {
        return currentNumberOfUniqueFlavorsRetainedAfterGiving
    }

    for i := 0; i < numberOfConsecutiveCandiesToGive; i++ {
        frequencyCandies[candies[i]]--
        if frequencyCandies[candies[i]] == 0 {
            currentNumberOfUniqueFlavorsRetainedAfterGiving--
        }
    }
    var maxNumberOfUniqueFlavorsRetainedAfterGiving = currentNumberOfUniqueFlavorsRetainedAfterGiving

    for i := numberOfConsecutiveCandiesToGive; i < len(candies); i++ {
        frequencyCandies[candies[i]]--
        if frequencyCandies[candies[i]] == 0 {
            currentNumberOfUniqueFlavorsRetainedAfterGiving--
        }

        frequencyCandies[candies[i-numberOfConsecutiveCandiesToGive]]++
        if frequencyCandies[candies[i-numberOfConsecutiveCandiesToGive]] == 1 {
            currentNumberOfUniqueFlavorsRetainedAfterGiving++
        }

        maxNumberOfUniqueFlavorsRetainedAfterGiving = max(
                maxNumberOfUniqueFlavorsRetainedAfterGiving,
                currentNumberOfUniqueFlavorsRetainedAfterGiving)
    }

    return maxNumberOfUniqueFlavorsRetainedAfterGiving
}

func createFrequencyCandies(candies []int) []int {
    maxCandyValue := 0
    for _, candy := range candies {
        maxCandyValue = max(maxCandyValue, candy)
    }

    frequencyCandies := make([]int, maxCandyValue+1)
    for _, candy := range candies {
        frequencyCandies[candy]++
    }
    return frequencyCandies
}

func findTotalNumberOfUniqueCandies(frequencyCandies []int) int {
    totalNumberOfUniqueCandies := 0
    for _, frequency := range frequencyCandies {
        if frequency > 0 {
            totalNumberOfUniqueCandies++
        }
    }
    return totalNumberOfUniqueCandies
}
