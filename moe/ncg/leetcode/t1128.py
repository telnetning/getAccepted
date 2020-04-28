class Solution(object):
    def numEquivDominoPairs(self, dominoes):
        """
        :type dominoes: List[List[int]]
        :rtype: int
        """
        sorted_list = sorted([sorted(inner_list) for inner_list in dominoes], key = lambda x: x[0] * 10 + x[1])

        # print(sorted_list)
        sum = 0
        cur_count = 1
        index = 0
        while(index < len(sorted_list)):
            if index != 0:
                if sorted_list[index][0]  == sorted_list[index-1][0] and sorted_list[index][1]  == sorted_list[index-1][1]:
                    cur_count += 1
                else:
                    sum += int(cur_count * (cur_count - 1) / 2)
                    cur_count = 1
            index +=1

        sum += int(cur_count * (cur_count - 1) / 2)
        return sum

dominoes = [[2,2],[1,2],[1,2],[1,1],[1,2],[1,1],[2,2]]
print(Solution().numEquivDominoPairs(dominoes=dominoes))
