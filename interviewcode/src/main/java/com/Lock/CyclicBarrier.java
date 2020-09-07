package com.Lock;

import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrier {

    public static void main(String[] args) {
        java.util.concurrent.CyclicBarrier cyclicBarrier = new java.util.concurrent.CyclicBarrier(7, () -> {
            System.out.println("**********召唤神龙");
        });

        for (int i = 0; i <=7 ; i++) {
            final  int tempint=i;
            new Thread(()->{
                System.out.println("******* \t "+Thread.currentThread().getName()+"获取了龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
