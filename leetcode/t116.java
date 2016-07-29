/*
 * 2016年 7月15日 星期五 21时11分21秒 CST
 * 这里使用了队列进行宽度优先搜索
 * 本题另外可直接使用迭代
 * 效率更高
 */
import java.util.Queue;
import java.util.LinkedList;

public class t116 {
    public static void main(String[] args) {
        TreeLinkNode t1 = new TreeLinkNode(1);         
        TreeLinkNode t2 = new TreeLinkNode(2);         
        TreeLinkNode t3 = new TreeLinkNode(3);         
        TreeLinkNode t4 = new TreeLinkNode(4);         
        TreeLinkNode t5 = new TreeLinkNode(5);         
        TreeLinkNode t6 = new TreeLinkNode(6);         
        TreeLinkNode t7 = new TreeLinkNode(7);         

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
             
        connect(t1);
    }

    public static void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> q = new LinkedList<>();          
        TreeLinkNode ptr = root;
        TreeLinkNode ptrTmp = null;
        int level = 0;
        
        if(root == null) return null;
        
        q.add(root);
        while(ptr.left != null) {
            for(int i = 0; i < Math.pow(2, level); i++) {
                ptr = q.poll();     
                if(i != 0) ptrTmp.next = ptr;
                ptrTmp = ptr;
                if(i == Math.pow(2, level)) ptr.next = null;

                q.add(ptr.left);
                q.add(ptr.right);
            } 
            level++;
        } //end while
    }
}

class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) {val = x;}
}
