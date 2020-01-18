package com.learning.algorithms.tree;

import java.util.Arrays;

public class MyBinaryHeap {
    private int[] binaryHeap;

    /**
     * 上浮操作
     * 新元素添加到二叉堆的最后一个结点位置，上浮操作将最后一个结点依次和父节点比较进行上浮。
     * @param binaryHeap    待调整的二叉堆数组
     * @author huangzihua
     * @date 2020-01-15
     */
    public static void upAdjust(int[] binaryHeap){
        int childIndex = binaryHeap.length-1;
        int parentIndex = (childIndex - 1)/2;
        // temp 保存插入的叶子节点值，用于最后的赋值
        int temp = binaryHeap[childIndex];
        while (childIndex > 0 && temp < binaryHeap[parentIndex]){
            binaryHeap[childIndex] = binaryHeap[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        binaryHeap[childIndex] = temp;
    }

    /**
     * 下沉
     * @param binaryHeap    待调整的二叉堆数组
     * @param parentIndex   待下沉的元素下标
     * @param length        该数组长度
     * @author huangzihua
     * @date 2020-01-15
     */
    public static void downAdjust(int[] binaryHeap, int parentIndex, int length){
        int temp = binaryHeap[parentIndex];
        int childIndex = 2 * parentIndex + 1;
        while (childIndex < length){
            if(childIndex+1 < length && binaryHeap[childIndex+1] < binaryHeap[childIndex]){
                childIndex++;
            }
            if(temp <= binaryHeap[childIndex]){
                break;
            }
            binaryHeap[parentIndex] = binaryHeap[childIndex];
            parentIndex = childIndex;
            childIndex = 2*childIndex+1;
        }
        binaryHeap[parentIndex] = temp;
    }

    /**
     * 构建二叉堆
     * @param array 待调整的数组
     * @author huangzihua
     * @date 2020-01-15
     */
    public static void buildHeap(int[] array){
        for (int i = (array.length-2)/2; i>=0; i--){
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        array = new int[] {7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }
}
