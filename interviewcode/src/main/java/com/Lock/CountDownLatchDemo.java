package com.Lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cl = new CountDownLatch(6);

        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国，被灭");
                cl.countDown();
            }, CountryEnum.foreach_countryEnum(i).getRetMsg()).start();
        }
        cl.await();
        System.out.println("***********\t 秦帝国一统华夏");
    }

    public static void closeDoor() throws InterruptedException {
        CountDownLatch cl = new CountDownLatch(6);

        for (int i = 0; i <6 ; i++) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"同学离开了教室");
            cl.countDown();
        },String.valueOf(i)).start();
    }
        cl.await();
        System.out.println("班长离开了教室");
    }
}
