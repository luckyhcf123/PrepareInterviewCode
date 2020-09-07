package com.test.dynamicProxy;

import java.lang.reflect.Proxy;

public class DynamicTest {
    public static void main(String[] args) {

        TargetImpl target = new TargetImpl();
        DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(target);

        Target proxyObject = (Target) Proxy.newProxyInstance(TargetImpl.class.getClassLoader(), TargetImpl.class.getInterfaces(), dynamicProxyHandler);
        String s = proxyObject.execute();
        System.out.println(s);
    }
}
