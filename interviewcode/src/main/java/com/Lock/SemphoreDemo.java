package com.Lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("******** \t"+Thread.currentThread().getName()+"占领车位");
                    TimeUnit.MILLISECONDS.sleep(3000);
                    System.out.println("********\t"+Thread.currentThread().getName()+"停了三秒钟以后离开了车位");
                }catch (Exception e){
                    //System.out.println(e.toString());
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }

}
