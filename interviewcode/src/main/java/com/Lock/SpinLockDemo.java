package com.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

//手写一个自旋锁
public class SpinLockDemo {
   AtomicReference<Thread> atomicReference= new AtomicReference<>();

   public void mylock(){
       Thread thread = Thread.currentThread();
      System.out.println(Thread.currentThread().getName()+"\t coming in");
      while(atomicReference.compareAndSet(null, thread)){
         // System.out.println(Thread.currentThread().getName()+"\t coming in 抢到了锁");
      }
   }

   public void myunlock(){
       Thread thread = Thread.currentThread();
       atomicReference.compareAndSet(thread, null);
       System.out.println(Thread.currentThread().getName()+"****\t invoke myunlock");
   }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        for (int i = 0; i <3 ; i++) {
            final int temp=i;
        new Thread(()->{
            spinLockDemo.mylock();
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
                System.out.println("hahahahah"+temp);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLockDemo.myunlock();
            }
        },String.valueOf(i)).start();
        }
    }
}
