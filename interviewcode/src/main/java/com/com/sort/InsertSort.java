package com.com.sort;

import java.util.ArrayList;
import java.util.Random;

//插入排序
public class InsertSort {
    ArrayList a1;

public InsertSort(int num,int mod){
    a1=new ArrayList(num);

    Random random=new Random();

    System.out.println("初始化数组列表前");
    for (int i = 0; i <num ; i++) {
        Integer integer=Math.abs(random.nextInt()%mod+1);
        a1.add(i,integer);
        System.out.print(integer+" ");
    }
}


   public void sort(){
    Integer tempInt;
    int maxSize=1;

       for (int i = 1; i < a1.size(); i++) {
           tempInt= (Integer) a1.remove(i);

           if(tempInt.intValue() >= ((Integer)a1.get(maxSize-1)).intValue()){

               a1.add(maxSize,tempInt);
               maxSize++;
               System.out.println(tempInt);

           }else{
               for (int j = 0; j <maxSize ; j++) {
                   if (((Integer)a1.get(j)).intValue()>=tempInt.intValue()){
                        a1.add(j,tempInt);
                        maxSize++;
                       System.out.println(tempInt);
                       break;
                   }
               }
           }

       }

       System.out.println("The ArrayList Sort After:");
       for(int i=0;i<a1.size();i++) {
           System.out.println("al["+i+"]="+a1.get(i));
       }
}

    public static void main(String[] args) {
        InsertSort insertSort = new InsertSort(10, 10);

        insertSort.sort();
    }

}
