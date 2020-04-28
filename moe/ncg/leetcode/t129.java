package moe.ncg.leetcode;

/**
 * 这里用一个 class 来当做一个结构体，并且作为一个 wrap 的结构体
 * 显得有些笨拙，但是因为 TreeNode 本身就已经被预定义无法更改了
 *
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.Stack;

public class t129 {
    static int res = 0;
    public int sumNumbers(TreeNode root) {
        res = 0;
        if(root == null) return 0;
        WrapTreeNode newRoot = new WrapTreeNode(root, root.val);
        WrapTreeNode ptr = newRoot;
        initWtn(ptr);
        return res;
    }
    
    public void initWtn(WrapTreeNode ptr) {
        if(ptr.tn.left == null && ptr.tn.right == null) {
            res += ptr.sum;
        }
        if(ptr.tn.left != null) {
            initWtn( new WrapTreeNode(ptr.tn.left, ptr.sum * 10 + ptr.tn.left.val));
        }
        
        if(ptr.tn.right != null) {
            initWtn( new WrapTreeNode(ptr.tn.right, ptr.sum * 10 + ptr.tn.right.val));
        }
    }
}

class WrapTreeNode {
    TreeNode tn;
    int sum;
    
    WrapTreeNode(TreeNode tn, int sum) {
        this.tn = tn;
        this.sum = sum;
    }
    
}
