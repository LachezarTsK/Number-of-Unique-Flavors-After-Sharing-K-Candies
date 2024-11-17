
#include <vector>
#include <algorithm>
using namespace std;

/*
 The code will run faster with ios::sync_with_stdio(0).
 However, this should not be used in production code and interactive problems.
 In this particular problem, it is ok to apply ios::sync_with_stdio(0).

 Many of the top-ranked C++ solutions for time on leetcode apply this trick,
 so, for a fairer assessment of the time percentile of my code
 you could outcomment the lambda expression below for a faster run.
*/

/*
 const static auto speedup = [] {
	ios::sync_with_stdio(0);
	return nullptr;
 }();
*/

class Solution {

public:
    int shareCandies(const vector<int>& candies, int numberOfConsecutiveCandiesToGive) const {
        vector<int> frequencyCandies = createFrequencyCandies(candies);
        int currentNumberOfUniqueFlavorsRetainedAfterGiving = findTotalNumberOfUniqueCandies(frequencyCandies);
        if (numberOfConsecutiveCandiesToGive == 0) {
            return currentNumberOfUniqueFlavorsRetainedAfterGiving;
        }

        for (size_t i = 0; i < numberOfConsecutiveCandiesToGive; ++i) {
            --frequencyCandies[candies[i]];
            if (frequencyCandies[candies[i]] == 0) {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }
        }
        int maxNumberOfUniqueFlavorsRetainedAfterGiving = currentNumberOfUniqueFlavorsRetainedAfterGiving;

        for (size_t i = numberOfConsecutiveCandiesToGive; i < candies.size(); ++i) {
            --frequencyCandies[candies[i]];
            if (frequencyCandies[candies[i]] == 0) {
                --currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }

            ++frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]];
            if (frequencyCandies[candies[i - numberOfConsecutiveCandiesToGive]] == 1) {
                ++currentNumberOfUniqueFlavorsRetainedAfterGiving;
            }

            maxNumberOfUniqueFlavorsRetainedAfterGiving = max(
                    maxNumberOfUniqueFlavorsRetainedAfterGiving,
                    currentNumberOfUniqueFlavorsRetainedAfterGiving);
        }

        return maxNumberOfUniqueFlavorsRetainedAfterGiving;
    }

private:
    vector<int> createFrequencyCandies(span<const int> candies) const {
        int maxCandyValue = 0;
        for (const auto& candy : candies) {
            maxCandyValue = max(maxCandyValue, candy);
        }

        vector<int> frequencyCandies(maxCandyValue + 1);
        for (const auto& candy : candies) {
            ++frequencyCandies[candy];
        }
        return frequencyCandies;
    }

    int findTotalNumberOfUniqueCandies(span<const int> frequencyCandies) const {
        int totalNumberOfUniqueCandies = 0;
        for (const auto& frequency : frequencyCandies) {
            if (frequency > 0) {
                ++totalNumberOfUniqueCandies;
            }
        }
        return totalNumberOfUniqueCandies;
    }
};
