
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int k;
    boolean solved;
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        this.solved = false;
        return travel(root);
    }

    public int travel(TreeNode root) {
        if(root.left != null) {
            int tmp;
            tmp = travel(root.left);
            if(this.solved) return tmp;
        }

        k--;
        if(k == 0) {
            this.solved = true;
            return root.val;
        }

        if(root.right != null) {
            int tmp;
            tmp = travel(root.right);
            if(this.solved) return tmp;
        }

        return 0;
    }
}