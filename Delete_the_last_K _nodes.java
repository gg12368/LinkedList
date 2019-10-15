5.删除倒数第K个节点：用例:
1,{1,2,3,4,5}	你的输出为:{1,2,3,4}

public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
    if(head==null||k<1){
        return head;
    }
        ListNode cur=head;
        while(cur!=null){
            k--;
            cur=cur.next;
        }
        if(k==0){
            head=head.next;
        }
        if(k<0){
            cur=head;
            while(++k!=0){
                cur=cur.next;
            }
            cur.next=cur.next.next;
        }
        return head;
    }
}
