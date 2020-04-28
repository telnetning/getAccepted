import java.util.Arrays;

/**
 * Created by telnetning on 16/8/7.
 */
public class t108 {
    public static void main(String[] args)
    {
        Solution108 s = new Solution108();
        int[] nums = {1,2,3,4,5,6};
        TreeNode t = s.sortedArrayToBST(nums);

        System.out.println(t.val);
        System.out.println(t.left.val);
        System.out.println(t.right.val);

    }

}

/**
 * Definition for a binary tree node.
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/*
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        if(nums.length == 1) return new TreeNode(nums[0]);

        if(nums.length == 2) {
            TreeNode t1 = new TreeNode(nums[0]);
            t1.right = new TreeNode(nums[1]);
            return t1;
        }

        int len = nums.length;
        int rootIndex =  (len + 1) / 2;  //root
        TreeNode root = new TreeNode(nums[rootIndex - 1]);
        int[] leftArray = new int[rootIndex - 1];
        int[] rightArray = new int[len - rootIndex];
        System.arraycopy(nums, 0, leftArray, 0 , rootIndex - 1);
        System.arraycopy(nums, rootIndex, rightArray, 0, len - rootIndex);
        root.left = sortedArrayToBST(leftArray);
        root.right = sortedArrayToBST(rightArray);

        return root;
    }
}
*/

class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        return satbst(nums, 0, nums.length - 1);
    }

    public TreeNode satbst(int[] nums, int start, int end)
    {
        int len = end - start + 1;
        if(len == 1) return new TreeNode(nums[start]);
        if(len == 2) {
            TreeNode t1 = new TreeNode(nums[start]);
            t1.right = new TreeNode(nums[end]);
            return t1;
        }
        int rootIndex = (end + start) / 2; //这里表示第rootIndex + 1个数, rootIndex为下标
        TreeNode root = new TreeNode(nums[rootIndex]);
        root.left = satbst(nums, start, rootIndex - 1);
        root.right = satbst(nums, rootIndex + 1, end);
        return root;
    }
}