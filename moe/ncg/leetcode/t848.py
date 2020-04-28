#!/usr/bin/env python2

class Solution(object):
    def shiftingLetters(self, S, shifts):
        """
        :type S: str
        :type shifts: List[int]
        :rtype: str
        """
        lenShirts = len(shifts)
        for i in range(lenShirts - 2, -1, -1):
            shifts[i] += shifts[i + 1]

        shifts = map(lambda x : x % 26, shifts)

        resultS = [''] * len(S)
        for i in range(min(len(S), lenShirts)):
            resultS[i] = chr((ord(S[i]) - ord('a') + shifts[i]) % 26 + ord('a'))

        return ''.join(resultS)


if __name__ == "__main__":
    print(Solution().shiftingLetters('bad', [10, 20, 30]))
