/*
 * 2016年 7月13日 星期三 01时15分13秒 CST
 * Max Howell 在 google 面试时的那道著名的题
 */
public class t226 {
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        if(root.left == null && root.right == null) return root;
        if(root.left != null) {
            root.left = invertTree(root.left); 
        } 
        if(root.right != null) {
            root.right = invertTree(root.right);
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        return root;
    } 
}
