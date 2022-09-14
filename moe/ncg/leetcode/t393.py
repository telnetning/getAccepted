class Solution:
    """
    有限状态机
    """
    def validUtf8(self, data: List[int]) -> bool:
        def intType(num: int) -> int:
            if num < 128:
                return 1
            if num < 128 + 64:
                return 5
            if num < 128 + 64 + 32:
                return 2
            if num < 128 + 64 + 32 + 16:
                return 3
            if num < 128 + 64 + 32 + 16 + 8:
                return 4
            return -1
        status = 0
        needed = 0 # for type 2~4, needed byte left
        for num in data:
            tmp = intType(num)
            if tmp == -1 or (tmp != 1 and status != 0 and tmp != 5):
                return False
            if tmp == 5 and needed == 0:
                return False
            if tmp == 5:
                needed -= 1
                if needed == 0:
                    status = 0
                continue
            if tmp == 1:
                continue
            status = tmp
            needed = tmp - 1
        return needed == 0