package com.bitree;

//public class Test {
public class ArrayToTree {
    public  static BiTNode arraytotree(int[] arr, int start, int end){
        BiTNode root=null;
        if (end>=start){
            root=new BiTNode();
            int mid=(start+end+1)/2;
            //树的根节点为数组中间的元素
            root.data=arr[mid];
            //递归的用左半部分的数组构造root的左子树
            root.lchild=arraytotree(arr, start,(mid-1));
            root.rchild=arraytotree(arr,(mid+1), end);
        }else{
            root=null;
        }
        return  root;
    }

    /*用中序遍历算法打印出二叉树结点的内容*/
    public  static  void printTreeMidOrder(BiTNode root){
        if(root==null) return;
        //遍历root节点的左子树
        if(root!=null){
            printTreeMidOrder(root.lchild);
        }
        //遍历root节点
        System.out.print(root.data+"  ");

        //遍历root的右子树
        if(root.rchild!=null){
            printTreeMidOrder(root.rchild);
        }
    }

    public static void main(String[] args){
        int arr[]={1,2,3,4,5,6,7,8,9,10};
        System.out.print("数组：");
        for (int i=0;i<arr.length;i++)
            System.out.print(arr[i]+"  ");
        System.out.println();
        BiTNode root;
        root=arraytotree(arr,0,arr.length-1);
        System.out.print("转换成树的中序遍历为：");
        printTreeMidOrder(root);
        System.out.println();
    }





}
