package com.Lock;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的实例
 *
 * */

    class MyCache1{ //资源类
       private  volatile HashMap<String,Object> map=new HashMap<>();
       ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();

       public  void put(String key,Object value){
           ReentrantReadWriteLock.WriteLock writeLock = rwlock.writeLock();
           try {
               writeLock.lock();
               System.out.println(Thread.currentThread().getName()+"准备写入"+key);
               try {
                   TimeUnit.MILLISECONDS.sleep(300);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               map.put(key,value);
               System.out.println(Thread.currentThread().getName()+"写入完成"+key);
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               writeLock.unlock();
           }
       }

       public void get(String key){
           ReentrantReadWriteLock.ReadLock readLock = rwlock.readLock();
           try {
               readLock.lock();
               System.out.println(Thread.currentThread().getName()+"准备获取"+key);
               try {
                   TimeUnit.MILLISECONDS.sleep(300);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               map.get(key);
               System.out.println(Thread.currentThread().getName()+"获取完成"+key);
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               readLock.unlock();
           }
       }

    }

public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache1 myCache = new MyCache1();
        for (int i = 0; i < 5; i++) {
            final int cishu=i;
            new Thread(()->{myCache.put(cishu+"",cishu+"");}
                        ,String.valueOf(cishu)).start();
        }

        for (int i = 0; i < 5; i++) {
            final int cishu=i;
            new Thread(()->{myCache.get(cishu+"");}
                    ,String.valueOf(cishu)).start();
        }

    }

}
