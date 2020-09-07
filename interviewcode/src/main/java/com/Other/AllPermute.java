package com.Other;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


//给定一个没有重复数字的序列，返回其所有可能的全排列
public class AllPermute {
    public static  List<List<Integer>> permute(int[] arr) {
        int len = arr.length;
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        //数组双端队列，初始值为16个整型值
        ArrayDeque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[len];

        dfs(arr, len, 0, path, used, res);

        return res;
    }

    private static void dfs(int[] arr, int len, int depth, ArrayDeque<Integer> path, boolean[] used, List<List<Integer>> res) {
            if(depth==len)
           {
                res.add(new ArrayList<>(path));
                return;
            }
        for (int i=0;i<len;i++) {
            if (used[i]){
                continue;
            }
            path.add(arr[i]);
            used[i]=true;

            dfs(arr,len,depth+1,path,used,res);
            path.removeLast();
            used[i]=false;

        }
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3};
        List<List<Integer>> list = permute(ints);
        Iterator<List<Integer>> iterator = list.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }

    }

}
