# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:

    def calTree(self, root:TreeNode):
        if root.left and self.calTree(root.left):
            root.left = None
        if root.right and self.calTree(root.right):
            root.right = None

        if (root.val == 0 and root.left == None and root.right == None):
            return True

        return False

    def pruneTree(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
        return root if not self.calTree(root) else None
