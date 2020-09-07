package com.sort_system;

public class SortTest {
    public static void main(String[] args) {
        User user1 = new User(1, "user1");
        User user2 = new User(2, "user2");
        User user3 = new User(3, "user3");
        User user4 = new User(4, "user4");
        MyQueue queue = new MyQueue();
        queue.enQueue(user1);
        queue.enQueue(user2);
        queue.enQueue(user3);
        queue.enQueue(user4);
        //队头的元素出队列
        queue.deQueue();
        //队中随意一个元素出队列
        queue.deQueue(user3);
        queue.enQueue(user1);
        queue.deQueue();
        queue.printList();
    }

}
