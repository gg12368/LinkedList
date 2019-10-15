7.删除链表中的重复元素II：给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
示例 1:输入: 1->2->3->3->4->4->5	输出: 1->2->5
示例 2:输入: 1->1->1->2->3	输出: 2->3

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
			return head;
		}
		ListNode dumb = new ListNode(0); //新增一个节点是链表类题目的小技巧，防止出现结果为空的情况
		ListNode p = dumb, q = head;
		while (q != null) {
			int count = 1;
			while (q.next != null && q.next.val == q.val) {// 统计当前数值的个数
				q = q.next;
				count++;
			}
			if (count == 1) {//如果超过一个，则为重复，直接看下一个
				p.next = q;
				p = p.next;
			} 
			q = q.next;
		}
		//这里有个点需要注意一下，必须将p.next置为null，不然最后会有多余元素
		p.next = null;
		return dumb.next;
    }
}
