package com.Lock;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class myThread implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("hahah******woshihuchuanfu");
        return 1024;
    }
}

public class CallableDemo {
    public static void main(String[] args) {

        FutureTask futureTask = new FutureTask<>(new myThread());

        new Thread(futureTask,"hu").start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
