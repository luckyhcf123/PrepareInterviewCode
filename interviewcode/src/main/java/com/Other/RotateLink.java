package com.Other;

public class RotateLink {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

        public static  ListNode rotateRight(ListNode head, int k) {
          /*  ListNode cur=head;
            for(int i=0;i<k;i++){
                while(cur.next.next!=null){
                    cur=cur.next;
                }
                cur.next=null;
                cur.next.next=head;
                head=cur.next;
            }
            return head;
        }*/

          if(head==null) return null;
          if (head.next==null) return  head;

          //先将链表头和链表尾巴相连
            ListNode old_tail=head;
            int i;
            for (i = 0; old_tail.next!=null ; i++) {
                old_tail=old_tail.next;
            }
            old_tail.next=head;

            //找到新的表头和新的表尾巴
            ListNode newtail=head;
            for (int n = 0; n <i-k%i-1 ; n++) {
                newtail=newtail.next;
            }

            ListNode newhead=newtail.next;

            newtail.next=null;

            return newhead;
        }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);


        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;
        listNode5.next=null;

      ListNode temp=listNode1;
      while (temp!=null){
          System.out.print(temp.val+" ");
          temp=temp.next;
      }
        System.out.println(" ");
        ListNode listNode = rotateRight(listNode1, 2);
        while(listNode!=null){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        };

    }
}
