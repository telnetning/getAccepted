import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(queue.size() > 0) {
            List<Integer> layer = new ArrayList<>();
            int currentLayerSize = queue.size();
            for(int i = 0; i < currentLayerSize; i++) {
                TreeNode curNode = queue.pollFirst();
                if(curNode.left != null) {
                    queue.addLast(curNode.left);
                }
                if(curNode.right != null) {
                    queue.addLast(curNode.right);
                }
                layer.add(curNode.val);
            }
            result.add(layer);
        }

        return result;
    }
}