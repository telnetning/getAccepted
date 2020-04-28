# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution(object):
    def lcaDeepestLeaves(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        递归处理，比较一个节点的left子节点和right的子节点的高度
        """
        def recuisive(root):
            """
            :param root: TreeNode
            :return height, resultNode: root height, and resultNode of root
            """
            if root.left == None and root.right == None:
                return root, 0
            if root.left == None and root.right != None:
                node, height =  recuisive(root.right)
                return node, height + 1
            if root.right == None and root.left != None:
                node, height =  recuisive(root.left)
                return node, height + 1

            lnode, lheight = recuisive(root.left)
            rnode, rheight = recuisive(root.right)

            if lheight > rheight:
                return lnode, lheight + 1

            if rheight > lheight:
                return rnode, rheight + 1

            return root, lheight + 1

        node, _ = recuisive(root)
        return node


