package com.sort_system;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueue {

    private Queue<User> q=new LinkedList<User>();
    public void enQueue(User u){
        u.setSeq(q.size()+1);
        q.add(u);
    }

    //方法的重载
    public void deQueue(){
        q.poll();
        updateSeq();
    }

    public void deQueue(User u){
        q.remove(u);
        updateSeq();

    }

    private void updateSeq() {
        int i=1;
        for (User u:q){
            u.setSeq(i++);
        }
    }

    void printList(){
        for (User u:q) {
            System.out.println(u);
        }
    }
}
