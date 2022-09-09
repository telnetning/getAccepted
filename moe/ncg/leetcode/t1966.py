class Solution:
    def numberOfWeakCharacters(self, properties: List[List[int]]) -> int:

        """
        最开始的想法，超时

        corner case: 还是要特别注意，x 轴相等的两个元素

        下面这个例子，说明了为啥要x轴和y轴相反去排序：
        [[8, 6], [8, 10], [9, 7], [9, 8]]
        倒着比较的时候，[8, 6] 要比 [8, 10] 先与 [9, 7] 或者 [9, 8] 比较，
        否则， 把 y轴 max 置成 10之后，对应的 x轴变成了 8，再拿 [8, 6] 去比较，
        就得到没有满足的元素的错误结果了

        """
        properties.sort(key=lambda x: (x[0], -x[1]))
        count = 0
        size = len(properties)
        max_defense = -1
        cur_max_defense_with_attack = -1
        for j in range(size-1, -1, -1):
            if properties[j][1] < max_defense and properties[j][0] != cur_max_defense_with_attack:
                count += 1
            elif properties[j][1] > max_defense:
                max_defense = properties[j][1]
                cur_max_defense_with_attack = properties[j][0]
            else:
                continue

        return count