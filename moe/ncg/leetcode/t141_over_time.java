package moe.ncg.leetcode;

/**
 * 题目要求不适用额外的空间
 * 于是得到这种暴力解法，然而超时
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class t141_over_time {
    public static boolean hasCycle(ListNode head) {
        if(head == null) return false;
        ListNode cur = head;
        ListNode travel = head;
        int i = 0;

        while(cur != null){
            for(int j = 0; j <= i ; j++) {
                if(travel == cur.next) return true;
                travel = travel.next;
            }
            cur = cur.next;
            i++;
            travel = head;
        }  //end if
        
        return false;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;
        n2.next = n1;
        System.out.println(hasCycle(n1));
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
