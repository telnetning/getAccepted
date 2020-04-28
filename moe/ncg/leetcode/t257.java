package moe.ncg.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class t257 { 
    static LinkedList<TreeNode> permu;
    static List<String> res;
    
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1); 
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        
        List<String> res = binaryTreePaths(t1);
        System.out.println(Arrays.toString(res.toArray()));
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        //init permu
        LinkedList<TreeNode> permu = new LinkedList<TreeNode>(); 
        List<String> res = new ArrayList<String>();
        if(root == null) return res;
        dfs(root, permu, res);
        return res;
    }
    
    public static void dfs(TreeNode root, LinkedList<TreeNode> permu, List<String> res) {
        permu.add(root);     

        if(root.left != null) dfs(root.left, permu, res);
        if(root.right != null) dfs(root.right, permu, res);
        if(root.left == null && root.right == null) {
            printLinkedList(permu, res);     
        }
        
        permu.removeLast();
    }
    
    public static void printLinkedList(LinkedList<TreeNode> permu, List<String> res) {
        String s = "";
        for(int i = 0; i < permu.size(); i++) {
            if(i == permu.size() - 1) {
                s += permu.get(i).val;      
            } else {
                s += permu.get(i).val + "->"; 
            }            
        } 
        res.add(s);
    }

}
//
//class TreeNode {
//    int val;
//    TreeNode left;
//    TreeNode right;
//    TreeNode(int x) {val = x;}
//}
