package com.Lock;

import java.util.concurrent.TimeUnit;

class  HoldLockThread implements Runnable{
    /* private String locka;
     private String lockb;*/
    private Integer locka;
    private Integer lockb;

     public HoldLockThread(Integer  locka, Integer lockb) {
         this.locka = locka;
         this.lockb = lockb;
     }

     @Override
     public void run() {
         synchronized (locka){
             System.out.println("已经持有"+locka+"尝试获取"+lockb);
             try {
                 TimeUnit.MILLISECONDS.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             synchronized (lockb){
                 System.out.println("huchuanfu");
             }
         }
     }
 }


public class DeadLockDemo {
    public static void main(String[] args) {
        Integer  locka1=1;
        Integer lockb1=2;

        new Thread(new HoldLockThread(locka1,lockb1),"AAA").start();
        new Thread(new HoldLockThread(lockb1, locka1),"BBB").start();
    }


}
