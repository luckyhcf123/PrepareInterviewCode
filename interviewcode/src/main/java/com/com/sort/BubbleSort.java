package com.com.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr){
        if (arr==null){
            return;
        }

    int length=arr.length;

        for (int i=0;i<length;i++){
            for(int j=0;j<length-1-i;j++){
                if (arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

        }
    }


    public static void main(String[] args) {

        int[] array={23,45,1,56,9,123,45,222};

        BubbleSort.sort(array);

        System.out.println(Arrays.toString(array));
    }
}
