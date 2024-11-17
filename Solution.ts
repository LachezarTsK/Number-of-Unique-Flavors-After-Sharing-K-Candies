
function shareCandies(candies: number[], numberOfConsecutiveCandiesToGive: number): number {
    const frequencyCandies = createFrequencyCandies(candies);
    let currentNumberOfUniqueFlavorsRetainedAfterGiving = findTotalNumberOfUniqueCandies(frequencyCandies);
    if (numberOfConsecutiveCandiesToGive === 0) {
        return currentNumberOfUniqueFlavorsRetainedAfterGiving;
    }

    for (let i = 0; i < numberOfConsecutiveCandiesToGive; ++i) {
        --frequencyCandies[candies[i]];
        if (frequencyCandies[candies[i]] === 0) {
            --currentNumberOfUniqueFlavorsRetainedAfterGiving;
        }
    }
    let maxNumberOfUniqueFlavorsRetainedAfterGiving = currentNumberOfUniqueFlavorsRetainedAfterGiving;

    for (let i = numberOfConsecutiveCandiesToGive; i < candies.length; ++i) {
        --frequencyCandies[candies[i]];
        if (frequencyCandies[candies[i]] === 0) {
            --currentNumberOfUniqueFlavorsRetainedAfterGiving;
        }

        ++frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]];
        if (frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]] === 1) {
            ++currentNumberOfUniqueFlavorsRetainedAfterGiving;
        }

        maxNumberOfUniqueFlavorsRetainedAfterGiving = Math.max(
            maxNumberOfUniqueFlavorsRetainedAfterGiving,
            currentNumberOfUniqueFlavorsRetainedAfterGiving);
    }

    return maxNumberOfUniqueFlavorsRetainedAfterGiving;
};

function createFrequencyCandies(candies: number[]): number[] {
    let maxCandyValue = 0;
    for (let candy of candies) {
        maxCandyValue = Math.max(maxCandyValue, candy);
    }

    const frequencyCandies = new Array(maxCandyValue + 1).fill(0);
    for (let candy of candies) {
        ++frequencyCandies[candy];
    }
    return frequencyCandies;
}

function findTotalNumberOfUniqueCandies(frequencyCandies: number[]): number {
    let totalNumberOfUniqueCandies = 0;
    for (let frequency of frequencyCandies) {
        if (frequency > 0) {
            ++totalNumberOfUniqueCandies;
        }
    }
    return totalNumberOfUniqueCandies;
}
