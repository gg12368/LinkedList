1.移出链表元素：删除链表中等于给定值 val 的所有节点。
输入: 1->2->6->3->4->5->6, val = 6输出: 1->2->3->4->5

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while(head!=null){
		if(head.val==val){
			head=head.next;
		}else{
			break;
		}
	}
	ListNode pre=null;
	ListNode cur=head;
	while(cur!=null){
		if(cur.val==val){
			pre.next=cur.next;
		}else{
			pre=cur;
		}
		cur=cur.next;
	}
	return head;
    }
}
