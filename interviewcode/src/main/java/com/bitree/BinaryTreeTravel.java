package com.bitree;

public class BinaryTreeTravel {

//    用前序遍历算法打印出二叉树结点的内容
    public static void printTreePreOrder(BiTNode root){
        if (root==null) return;
        //遍历root节点
        System.out.println(root.data);
        //先序遍历root的左结点
        if (root.lchild!=null){
            printTreePreOrder(root.lchild);
        }
        //先序遍历root的右结点
        if(root.rchild!=null){
            printTreePreOrder(root.rchild);
        }
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


    //    用后序遍历算法打印出二叉树结点的内容
    public static void printTreePostOrder(BiTNode root){
        if (root==null) return;
        //先序遍历root的左结点
        if (root.lchild!=null){
            printTreePostOrder(root.lchild);
        }
        //先序遍历root的右结点
        if(root.rchild!=null){
            printTreePostOrder(root.rchild);
        }

        //遍历root节点
        System.out.println(root.data);
    }

    public static void main(String[] args) {

        BiTNode root = new BiTNode();
        BiTNode biTNode2 = new BiTNode();
        BiTNode biTNode3 = new BiTNode();
        BiTNode biTNode4 = new BiTNode();
        BiTNode biTNode5 = new BiTNode();
        BiTNode biTNode6 = new BiTNode();
        BiTNode biTNode7 = new BiTNode();

        root.data=4;
        biTNode2.data=2;
        biTNode3.data=6;
        biTNode4.data=1;
        biTNode5.data=3;
        biTNode6.data=5;
        biTNode7.data=7;

        root.lchild=biTNode2;
        root.rchild=biTNode3;
        biTNode2.lchild=biTNode4;
        biTNode2.rchild=biTNode5;
        biTNode3.lchild=biTNode6;
        biTNode3.rchild=biTNode7;

       // BinaryTreeTravel.printTreePreOrder(root);
        BinaryTreeTravel.printTreePostOrder(root);
    }




}
