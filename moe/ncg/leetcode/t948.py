class Solution:
    # Greedy
    def bagOfTokensScore(self, tokens: List[int], power: int) -> int:
        i, j = 0, len(tokens) - 1
        score = 0
        tokens.sort()
        while i <= j:
            if power >= tokens[i]:
                power -= tokens[i]
                score += 1
                i += 1
                continue
            if score > 0:
                power += tokens[j] - tokens[i]
                j -= 1
                i += 1
                continue
            break

        return score
