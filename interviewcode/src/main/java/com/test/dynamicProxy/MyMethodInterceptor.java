package com.test.dynamicProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {


    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("MyMethodInterceptor--------start--------");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("MyMethodInterceptor---------stop---------");
        return "result";
    }
}
