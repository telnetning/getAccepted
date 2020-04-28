/**
 * Created by telnetning on 16/8/12.
 */
import java.util.*;
public class t143 {
    public static void main(String[] args) {
        ListNode143 t1 = new ListNode143(1);
        ListNode143 t2 = new ListNode143(2);
        ListNode143 t3 = new ListNode143(3);
        ListNode143 t4 = new ListNode143(4);
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        Solution143 s = new Solution143();
        s.reorderList(t1);

        ListNode143 node;
        node = t1;
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}

class ListNode143 {
    int val;
    ListNode143 next;
    ListNode143(int x) { val = x; }
}

class Solution143 {
   public void reorderList(ListNode143 head) {
       if(head == null || head.next == null) return;

       ListNode143 cur = head;
       int len = 0;
       while(cur != null) {
           len++;
           cur = cur.next;
       }

       ListNode143[] nodeArr = new ListNode143[len];

       int i = 0;
       cur = head;
       while(cur != null) {
           nodeArr[i] = cur;
           i++;
           cur = cur.next;
       }

       cur = head;

       int m = 0;
       int start = 1;
       int end = len - 1;
       while(start < end) {
           if(m % 2 == 0) {
               cur.next = nodeArr[end];
               end--;
           } else {
               cur.next = nodeArr[start];
               start++;
           }
           cur = cur.next;
           m++;
       }
       cur.next = nodeArr[start];
       cur.next.next = null;
   }

}
