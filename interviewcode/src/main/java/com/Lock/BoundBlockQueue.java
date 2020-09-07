package com.Lock;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//设计有限的阻塞队列
public class BoundBlockQueue {
    AtomicInteger size=new AtomicInteger(0);
    private volatile  int capacity;
    private LinkedList<Integer> container;
    private Lock lock= new ReentrantLock();
    //依据可重入锁new出不同的condition
    Condition producer=lock.newCondition();
    Condition consumer=lock.newCondition();

    public BoundBlockQueue(int capacity) {
        this.capacity = capacity;
        container=new LinkedList<>();
    }

    //入队操作
    public void enqueue(int element){
        lock.lock();
        try {
            while(size.get()>=capacity){
                //入队线程阻塞，把锁释放
                try {
                    producer.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
            container.addFirst(element);
            size.incrementAndGet();
            //通知出队队列
            consumer.signal();
        }finally {
            lock.unlock();
        }

    }

    //出队操作
    public int dequeue(){
        lock.lock();
        try {
        while (size.get()==0){
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            int last1 = container.getLast();
            container.removeLast();
            size.decrementAndGet();
            producer.signal();

            return last1;

        }finally {
            lock.unlock();
        }


    }

    //获取阻塞队列中当前拥有的元素个数
    public int size(){

        lock.lock();
        try {
            return size.get();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        BoundBlockQueue boundBlockQueue = new BoundBlockQueue(3);
        boundBlockQueue.enqueue(1);
        boundBlockQueue.enqueue(2);
        boundBlockQueue.enqueue(3);
        boundBlockQueue.enqueue(4);

        System.out.println(boundBlockQueue.size());
    }
}
