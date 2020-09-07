package com.test.dynamicProxy;

import net.sf.cglib.proxy.Enhancer;

public class MyMethodInterceptorTest {
    public static void main(String[] args) {
        TargetImpl target = new TargetImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TargetImpl.class);
        enhancer.setCallback(new MyMethodInterceptor());
        Target proxyTarget = (Target) enhancer.create();
        String res = proxyTarget.execute();
        System.out.println(res);

    }

}
