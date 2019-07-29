class Solution:
    def alphabetBoardPath(self, target: str) -> str:
        result = ''
        before = (0, 0)
        for c in target:
            (x, y) = self.get_x_y(c)
            x_flag = x - before[0] > 0
            y_flag = y - before[1] > 0
            if not self.is_z(before[0], before[1]):
                for i in range(0, abs(x - before[0])):
                    result += 'R' if x_flag else 'L'
                for i in range(0, abs(y - before[1])):
                    result += 'D' if y_flag else 'U'
            else:
                for i in range(0, abs(y - before[1])):
                    result += 'D' if y_flag else 'U'
                for i in range(0, abs(x - before[0])):
                    result += 'R' if x_flag else 'L'

            result += '!'
            before = (x, y)
        return result

    def is_z(self, x, y):
        return x == 0 and y == 5

    def get_x_y(self, char):
        board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"]
        diff = ord(char) - ord('a')
        return (diff % 5, int(diff / 5))

print(Solution().alphabetBoardPath('zb'))
