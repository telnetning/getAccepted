import java.util.List;

/**
 * Created by telnetning on 16/8/6.
 */


public class t203 {
    public static void main(String[] args)
    {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        ListNode n7 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;

        Solution203 s = new Solution203();
        ListNode tmp = s.removeElements(n1, 6);
        while(tmp != null)
        {
            System.out.println(tmp.val);
            tmp = tmp.next;
        }
    }

}

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre, cur, root;
        root = head;
        while(root != null && root.val == val) root = root.next;
        if(root == null) return root;
        cur = root.next;
        pre = root;
        while(cur != null)
        {
            if(cur.val == val) {
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return root;
    }
}
