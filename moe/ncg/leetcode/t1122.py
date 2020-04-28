class Solution(object):
    def relativeSortArray(self, arr1, arr2):
        """
        :type arr1: List[int]
        :type arr2: List[int]
        :rtype: List[int]
        """
        diff = []
        arr2Count = {}
        for i in arr2:
            arr2Count[i] = 0
        for i in arr1:
            if i in arr2Count.keys():
                arr2Count[i] += 1
            else:
                diff.append(i)
        rlist = []
        for i in arr2:
            for j in range(arr2Count[i]):
                rlist.append(i)
        print(rlist)
        print(diff)
        list.sort(diff)
        rlist.extend(diff)
        return rlist

arr1 = [2,3,1,3,2,4,6,7,9,2,19]
arr2 = [2,1,4,3,9,6]
print(Solution().relativeSortArray(arr1, arr2))


