package com.com.sort;


//java的util中提供了默认小根堆的PriorityQueue,如果要弄成大根堆的话则需重写一下比较器
import java.util.Arrays;
import java.util.PriorityQueue;

public class TopKSolution {

    public static int[] getleastnumber(int[] arr,int k) {

        if (arr.length==0 || k==0){
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);

        for (int i = 0; i <arr.length ; i++) {
            if (k>=queue.size()){
                queue.offer(arr[i]);
            }else if (arr[i]<queue.peek()){
                queue.poll();
                queue.offer(arr[i]);
               }
        }

        int[] res = new int[queue.size()];
        int ids=0;
        for (int num:queue) {
            res[ids++]=num;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] arr = {4, 2, 7, 1, 2, 9, 6, 7, 22};
        int[] ints = getleastnumber(arr, 3);
        System.out.println(Arrays.toString(ints));
    }
}
