package com.Other;

import java.lang.reflect.Array;

//运用反射机制声明长度为10的int型号数组
public class IntArrayDemo {
    public static void main(String[] args) {
        Class c = int.class;
        Object o = Array.newInstance(c, 10);
        for (int i = 0; i <10 ; i++) {
            Array.set(o,i,i);
        }
        int[] arr=(int[]) o;
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }


    }

}
