package moe.ncg.leetcode;
/**
 * 递归
 * Definition for a binary tree node.
 */
//class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode(int x) { val = x; }
//}

public class t112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.val == sum && root.left == null && root.right == null) return true;
        
        if(hasPathSum(root.left, sum - root.val)){
            return true;
        } else {
            return hasPathSum(root.right, sum - root.val);
        }
    }
}
