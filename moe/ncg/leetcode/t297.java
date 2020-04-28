package moe.ncg.leetcode;

/**
 * Created by telnetning on 16/8/8.
 */

import java.util.*;

public class t297 {
    public static void main(String[] args)
    {
        TreeNode297 t1 = new TreeNode297(-1);
        TreeNode297 t2 = new TreeNode297(2);
        TreeNode297 t3 = new TreeNode297(-3);
        TreeNode297 t4 = new TreeNode297(42);
        TreeNode297 t5 = new TreeNode297(587);
        TreeNode297 t6 = new TreeNode297(6);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        t5.left = t6;

        Codec c = new Codec();
        System.out.println(c.serialize(t1));

        TreeNode297 root = c.deserialize(c.serialize(t1));
        System.out.println("root val:" + root.val);
        System.out.println(c.serialize(root));
    }

}

/*
 * Definition for a binary tree node.
 */
class TreeNode297 {
    int val;
    TreeNode297 left;
    TreeNode297 right;
    TreeNode297(int x) { val = x; }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode297 root) {
        if(root == null) return "";
        TreeNode297 tmp = new TreeNode297(-1);
        StringBuilder str = new StringBuilder("");
        //use bfs
        Queue<TreeNode297> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode297 cur;
        while(queue.size() != 0)
        {
            cur = queue.remove();
            if(cur == tmp)
            {
                str.append("/x");
                continue;
            }

            str.append( "/" + cur.val);
            if(cur.left != null)
            {
                queue.add(cur.left);
            } else {
                queue.add(tmp);
            }

            if(cur.right != null)
            {
                queue.add(cur.right);
            } else {
                queue.add(tmp);
            }

            System.out.println(queue.size());
        }
        return str.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode297 deserialize(String data) {
        System.out.println(data);
        if(data == "") return null;
        String[] dataArr = data.split("/");
        int len = dataArr.length;

        Deque<TreeNode297> queue = new ArrayDeque();
        queue.add(new TreeNode297(Integer.parseInt(dataArr[1])));
        TreeNode297 root = queue.peek();
        TreeNode297 cur;
        TreeNode297 tmp;
        int i = 1;
        while(queue.size() != 0)
        {
            cur = queue.remove();
            i++;
            if(dataArr[i].equals("x"))
            {
                cur.left = null;
            } else {
                cur.left = new TreeNode297(Integer.parseInt(dataArr[i]));
                queue.add(cur.left);
            }

            i++;
            if(dataArr[i].equals("x"))
            {
                cur.right = null;
            } else {
                cur.right = new TreeNode297(Integer.parseInt(dataArr[i]));
                queue.add(cur.right);
            }

        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
