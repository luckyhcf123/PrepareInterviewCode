package com.com.sort;


import java.util.Arrays;

//快速排序 1，找到数组中的第一个数作为基准
//2，从数组的最后位置开始向前找到第一个基准小的，将基准与之交换，然后从前面开始找第一个比基准大的，将基准与之交换
//3.对分组后的每个组都进行第二步骤的操作
public class QuickSort1 {
    public static void  quicksort(int[] arr){
        quicksort(arr,0,arr.length-1);
    }

    public static void quicksort(int[] arr,int left,int right){
        if(arr==null || left>=right ||arr.length<=1){
            return;
        }

        int mid=partition(arr,left,right);
        quicksort(arr,left,mid);
        quicksort(arr,mid+1,right);

    }

    public static int partition(int[] arr,int left,int right) {
        int temp = arr[left];
        while (right > left) {
            while (left < right && temp <= arr[right]) {
                --right;
            }
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }

            while (left < right && temp >= arr[left]) {
                ++left;
            }

            if (left < right) {

                arr[right] = arr[left];
                --right;
            }
        }
            arr[left] = temp;
            return left;

    }


    public static void main(String[] args) {
        int[] arr={4,1,2,9,10,23,9,99};
        quicksort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
