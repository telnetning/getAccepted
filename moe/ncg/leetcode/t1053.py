class Solution:
    '''
    ele   ele_i  ele  ele_j
    条件：
        ele_i > ele_j
        ele_i 下标 < ele_j 下标
    ->
        ele_i 下标越大越好，即 ele_i 越往后越好
        要注意的点：
            1. 确定一组 ele_i 和 ele_j 之后，要看看在 ele_i 和 ele_j 之间有没有等于 ele_j 的，
                如果有，这个解更好   ele_i ele ele_j(better)  ele  ele_j(wrong answer)
            2. 确定一组 ele_i 和 ele_j 之后，要确认在这两个数之间，是否还有解
                ele_i ele_k ele_t ele_j
                如果 ele_i 和 ele_j 是找到的一组解，那么在两者之间又找到 ele_k 和 ele_t，
                这个解更好
    corner case:
        [3,1,1,3]
        [4,5,3,1,1,3]

    '''
    def prevPermOpt1(self, arr: List[int]) -> List[int]:
        size = len(arr)
        for i in range(size-1, 0, -1):
            cur = arr[i]
            last_equal_index = i
            for j in range(i-1, -1, -1):
                if arr[i] > arr[j]:
                    continue
                elif arr[i] == arr[j]:
                    last_equal_index =j
                else:
                    subResult = self.prevPermOpt1(arr[j+1:i])
                    if subResult == arr[j+1:i]:
                        arr[last_equal_index], arr[j] = arr[j], arr[last_equal_index]
                        return arr
                    else:
                        return arr[:j+1] + subResult + arr[i:]

        return arr