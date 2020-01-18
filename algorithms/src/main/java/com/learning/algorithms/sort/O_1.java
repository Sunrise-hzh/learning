package com.learning.algorithms.sort;

import java.util.Arrays;

/**
 * 时间复杂度为线性O(1)的排序算法：（不基于元素之间的比较）
 * 1.计数排序
 * 2.桶排序
 * 3.基数排序
 */
public class O_1 {
    public static void main(String[] args) {
        int[] array = new int[] {95,98,99,101,97,102,94,96,103,100};
        int[] sortedArray = countSort(array);
        System.out.println(Arrays.toString(sortedArray));
    }

    //计数排序
    public static int[] countSort(int[] array){
        // 1.得到数列的最大值
        int maxValue = array[0];
        int minValue = array[0];
        for (int i = 1; i < array.length; i++){
            if(array[i] > maxValue){
                maxValue = array[i];
            }
            if(array[i] < minValue){
                minValue = array[i];
            }
        }
        // 2.根据数列最大值确定统计数组的长度
        int[] countArray = new int[maxValue-minValue+1];
        // 3.遍历数列，填充统计数组
        for (int i = 0; i < array.length; i++){
            countArray[array[i]-minValue]++;
        }
        // 4.遍历统计数组，输出结果
        int[] sortedArray = new int[array.length];
        int sourtedIndex = 0;
        for (int i = 0; i < countArray.length; i++){
            for (int j = 0; j < countArray[i]; j++){
                sortedArray[sourtedIndex] = i+minValue;
                sourtedIndex++;
            }
        }
        return sortedArray;
    }
}
