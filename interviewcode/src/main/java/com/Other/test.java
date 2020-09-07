package com.Other;

public class test {

    public static void getAllSubset(char[] array,int[] mask,int c){
        int length=array.length;
        int i;
        if(length==c){
            System.out.print("{");
            for (i=0;i<length;i++){
                if (mask[i]==1){
                    System.out.print(array[i]+"");
                }
            }
            System.out.print("}");

        }else{
            mask[c]=1;
            getAllSubset(array,mask,c+1);
            mask[c]=0;
            getAllSubset(array,mask,c+1);
        }
    }

    public static void main(String[] args) {
        char[] arr={'a','b','c'};
        int [] mask={0,0,0};
        getAllSubset(arr,mask,0);
       // System.out.println(1>>>1);
    }

}
