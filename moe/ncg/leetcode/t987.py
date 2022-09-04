# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    
    def travelTree(self, root: TreeNode, rowIndex: int, colIndex: int, colDict: dict):
        colDict[colIndex].extend([(root.val, rowIndex, colIndex)])
        
        if root.left:
            self.travelTree(root.left, rowIndex + 1, colIndex - 1, colDict)
        if root.right:
            self.travelTree(root.right, rowIndex + 1, colIndex + 1, colDict)
    
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        colDict = defaultdict(list) # {int -> list[(val, rowIndex, colIndex), ()]}
        result = [] 
        self.travelTree(root, 0, 0, colDict)
        
        for i in sorted(list(colDict.keys())):
            result.append(k[0] for k in sorted(colDict[i], key = lambda x: (x[1], x[2], x[0])))
            
        return result
