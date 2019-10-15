8.回文链表：请判断一个链表是否为回文链表。
示例 1:输入: 1->2输出: false
示例 2:输入: 1->2->2->1输出: true
这道题可以利用栈结构来求解，但其实并不需要将所有的节点都压入栈中，只用压入一般的节点即可。首先假设链表的长度为N，如果N是偶数，前N/2的节点叫做左半区，后N/2的节点叫做右半区。如果N是奇数，忽略处于最中间的节点，还是前N/2的节点叫做左半区，后N/2的节点叫做右半区。
例如：
链表：1->2->2->1,左半区为1,2；右半区为2,1。
链表：1->2->3->2->1,左半区为1,2；右半区为2,1。
例如：
链表：1->2->2->1,链表的右半部分压入栈中后，从栈顶到栈底为1,2.链表的左半部分也是1,2.所以这个链表是回文结构。

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head==null||head.next==null){
            return true;
        }
        ListNode slow=head.next;
        ListNode fast=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        Stack<ListNode> stack=new Stack<ListNode>();
        while(slow!=null){
            stack.push(slow);
            slow=slow.next;
        }
        while(!stack.isEmpty()){
            if(head.val!=stack.pop().val){
                return false;
            }
            head=head.next;
        }
        return true;
    }
}
