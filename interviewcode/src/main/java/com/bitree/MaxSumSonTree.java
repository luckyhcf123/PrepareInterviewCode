package com.bitree;

public class MaxSumSonTree
{

   public static int maxSum=Integer.MIN_VALUE;

    //构建二叉树
     public static BiTNode constructTree(){

    //构建节点
    BiTNode root = new BiTNode();
    BiTNode node1 = new BiTNode();
    BiTNode node2 = new BiTNode();
    BiTNode node3 = new BiTNode();
    BiTNode node4 = new BiTNode();
    //初始化节点值
    root.data=6;
    node1.data=3;
    node2.data=-7;
    node3.data=-1;
    node4.data=9;
    //串联节点成树
    root.lchild=node1;
    root.rchild=node2;
    node1.lchild=node3;
    node1.rchild=node4;

    return  root;
}

    private static int getMaxSumTree(BiTNode root, BiTNode maxSumNode) {
         if (root==null) {
             return 0;
         }
         int lmaxSum=getMaxSumTree(root.lchild,maxSumNode);
         int rmaxSum=getMaxSumTree(root.rchild,maxSumNode);
         int sum=lmaxSum+rmaxSum+root.data;

         if (sum>maxSum){
             maxSum=sum;
             maxSumNode.data=root.data;
         }
         return sum;
    }

    public static void main(String[] args) {
         BiTNode root= MaxSumSonTree.constructTree();
         BiTNode maxSumNode=new BiTNode();
         MaxSumSonTree.getMaxSumTree(root,maxSumNode);

         //最大子树节点值
         System.out.println(maxSumNode.data);
         //最大子树的和值
        System.out.println(maxSum);
    }


}
