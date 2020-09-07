package com.test.dynamicProxy;

public class ProxyTest {

    public static void main(String[] args) {

        Target target = new TargetImpl();
        Proxy p = new Proxy(target);
        String result =  p.execute();
        System.out.println(result);
    }
}
