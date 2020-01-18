package com.learning.algorithms.sort;

import java.util.Arrays;

/**
 * 时间复杂度为O(n^2)的排序算法
 * 1.冒泡排序（特殊冒泡：鸡尾酒排序，双向交换）
 * 2.选择排序
 * 3.插入排序
 * 4.希尔排序（希尔排序比较特殊，它的性能略优于O(n^2)，但又比不上O(nlogn)，所以归入本类）
 */
public class O_N2 {

    /**
     * 冒泡排序
     * 思想：将相邻的两个元素互相比较，若前一个元素大于后一个元素，则交换他们的位置，否则不变。
     * 时间复杂度：O(n^2)
     * 特性：稳定排序
     */
    public static void bubbleSort(int[] array){
        for (int i = 0; i < array.length-1; i++){
            for (int j = 0; j < array.length-i-1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序优化
     * 一、增加有序标记(isOrdered)，记录当次循环是否发生交换，若无则说明数列已经有序，不用再执行后面的循环
     * 二、增加无序数列的边界(sortBorder)、发生最后一次交换的位置(lastSwapIndex)，
     *      每次循环只遍历到无序边界，并记录最后一次发生交换位置，然后赋值给sortBorder，
     *      作为下次循环的边界，下一次遍历时则不会重复比较有序的部分。
     * @param array
     */
    public static void bubbleSort_2(int[] array){
        //记录最后一次交换的位置
        int lastSwapIndex = 0;
        //无序数列的边界
        int sortBorder = array.length-1;
        for (int i = 0; i < array.length-1; i++){
            //有序标记，默认为true，若发生一次交换，则置为false
            boolean isOrdered = true;
            for (int j = 0; j < sortBorder; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    isOrdered = false;  //有元素交换，则标记为false
                    lastSwapIndex = j;  //记录最后一次交换的位置
                }
            }
            sortBorder = lastSwapIndex;
            if(isOrdered){  //如果没有元素交换，证明数列全部有序，跳出循环
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{2,1,4,5,8,7,6,3,12,9,11,10};
        bubbleSort_2(array);
        System.out.println(Arrays.toString(array));
    }
}
