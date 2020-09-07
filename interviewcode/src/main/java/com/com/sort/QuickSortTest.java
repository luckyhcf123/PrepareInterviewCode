package com.com.sort;

import java.util.Arrays;

public class QuickSortTest {

    /**
     * Description: 快速排序
     *
     * @param array
     * @return void
     * @author JourWon
     * @date 2019/7/11 23:39
     *
     * 快速排序思路：1.从数列中取出一个数作为基本数
     *              2.分区过程，将比基本数大的放在基本数的右边，将比基本数小的放在基本数的左边。
     *              3.对左右区间各自进行第二步，直至各个区间中的内容为1。
     *
     *     1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
     *     2．j–由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
     *     3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
     *     4 ．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
     */
    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }


    private static void quickSort(int[] array, int left, int right) {
        if (array == null || left >= right || array.length <= 1) {
            return;
        }
        int mid = partition(array, left, right);
        quickSort(array, left, mid);
        quickSort(array, mid + 1, right);
    }


    private static int partition(int[] array, int left, int right) {
        int temp = array[left];
        while (right > left) {
            // 先判断基准数和后面的数依次比较
            while (temp <= array[right] && left < right) {
                --right;
            }
            // 当基准数大于了 arr[left]，则填坑
            if (left < right) {
                array[left] = array[right];
                ++left;
            }
            // 现在是 arr[right] 需要填坑了
            while (temp >= array[left] && left < right) {
                ++left;
            }
            if (left < right) {
                array[right] = array[left];
                --right;
            }
        }
        array[left] = temp;
        return left;
    }


    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        // 只需要修改成对应的方法名就可以了
        quickSort(array);

        System.out.println(Arrays.toString(array));
    }
}
