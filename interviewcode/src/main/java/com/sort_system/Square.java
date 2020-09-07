package com.sort_system;

public class Square {

    public float squareRoot(float n,float e){
        float new_one=n;
        float last_one=1;
        while (new_one-last_one>e){
            new_one=(new_one+last_one)/2;
            last_one=n/new_one;
        }
        return  new_one;
    }

    public static void main(String[] args) {
        Square s=new Square();
        int n=50;
        float e=0.000001f;
        System.out.println(n+"的算术平方根为"+s.squareRoot(n,e));
        n=4;
        System.out.println(n+"的算术平方根为"+s.squareRoot(n,e));
    }


}
