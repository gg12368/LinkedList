2.反转一个链表：反转一个单链表。
输入: 1->2->3->4->5->NULL输出: 5->4->3->2->1->NULL

class Solution {
    public ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
    }
}
