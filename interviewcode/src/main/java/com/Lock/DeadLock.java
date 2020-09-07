package com.Lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

   ReentrantLock lock=  new ReentrantLock();
    ReentrantLock lock1=  new ReentrantLock();

   public  void method1(){
       try {
           lock.lock();
           System.out.println(Thread.currentThread().getName()+"我是方法一");
           TimeUnit.MILLISECONDS.sleep(300);
           lock1.lock();
           //method2();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
           lock1.unlock();
       }
   }

   public  void method2(){
       try {
           lock1.lock();
           System.out.println(Thread.currentThread().getName()+"我是方法二");
           TimeUnit.MILLISECONDS.sleep(300);
           lock.lock();
           method1();
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock1.unlock();
           lock.unlock();
       }

   }


    public static void main(String[] args) {

        DeadLock deadLock = new DeadLock();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"*****xiancheng");
                deadLock.method1();
               deadLock.method2();
            },String.valueOf(i)).start();
    }
    }
}
