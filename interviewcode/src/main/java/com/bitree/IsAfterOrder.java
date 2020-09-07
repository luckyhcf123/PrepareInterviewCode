package com.bitree;

public class IsAfterOrder {

    public static boolean IsAfterOrder(int[] arr,int start,int end){
    if (arr==null) return false;
    int root=arr[end];
    int i,j;
        for (i = 0;  i< end; i++) {
            if (arr[i]>root){
                break;
            }
        }
        System.out.println("---------------------"+i);
        for (j = i; j <end ; j++) {
            if (arr[j]<root) return false;
        }

        boolean left_isAfterOrder=true;
        boolean right_isAfterOrder=true;

        if (i>start){
            left_isAfterOrder=IsAfterOrder(arr,start,i-1);
        }
        if (j<end){
            right_isAfterOrder=IsAfterOrder(arr,i,end);
        }
        return (left_isAfterOrder&&right_isAfterOrder);
    }

    public static void main(String[] args) {
        int[] arr={1,3,2,5,7,6,4};
        boolean result=IsAfterOrder(arr,0,arr.length-1);
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }
        if (result){
            System.out.println("是某个二元查找树的后续遍历");
        }else{
            System.out.println("不是某个二元查找树的后续遍历");
        }
    }

}
