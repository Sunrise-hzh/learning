package com.learning.algorithms.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 时间复杂度为O(nlogn)的排序算法：
 * 1.快速排序（ 不稳定排序，最坏的排序时间是O(n^2)，递归和非递归的空间复杂度都是O(logn) ）
 * 2.归并排序
 * 3.堆排序（ 不稳定排序，最坏的排序时间依然稳定在O(nlogn)，空间复杂度为O(1) ）
 */
public class O_NLogN {
    public static void main(String[] args) {
        int[] arr = new int[] {2,1,4,5,8,7,6,3,12,9,11,10};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 最大堆排序
     * 利用二叉堆的特性，最大/小堆的堆顶是整个堆中的最大/小元素。
     * 首先将数列构建成二叉堆，
     * 然后通过循环将堆顶元素（数列的最大/小值）和最后一个元素交换，
     * 接着将堆的大小减1（排序边界，区别于数列大小），同时将交换后的堆顶元素下沉，最后循环结束则得到排序后的数列。
     * @param array 数列
     * @author huangzihua
     * @date 2020-01-18
     */
    public static void heapSort(int[] array){
        //构建二叉堆
        for(int i = (array.length-2)/2; i >=0; i--){
            downAdjust(array, i, array.length);
        }
        System.out.println(Arrays.toString(array));
        //堆排序
        for (int i = array.length-1; i > 0; i--){
            //将堆顶元素和最后一个元素交换
            int top = array[0];
            array[0] = array[i];
            array[i] = top;
            //将栈顶元素下沉
            downAdjust(array, 0, i);
        }
    }

    /**
     * 下沉操作（用于实现最大堆排序）
     * @param array 数列
     * @param parentIndex 下沉元素的下标
     * @param size 堆实际大小
     * @author huangzihua
     * @date 2020-01-18
     */
    public static void downAdjust(int[] array, int parentIndex, int size){
        //获取栈顶元素
        int temp = array[parentIndex];
        //计算孩子下标位置
        int childIndex = parentIndex * 2 + 1;
        while (childIndex < size) {
            //比较左右孩子大小，取最大的孩子结点的下标
            if(childIndex < size-1 && array[childIndex] < array[childIndex+1]){
                childIndex++;
            }
            //比较父结点和孩子结点，若父结点大，则跳出循环，不用继续下沉
            if(temp >= array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = childIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 使用栈来实现快速排序（单边循环）
     * @param array 数列
     * @param startIndex 起始位置
     * @param endIndex 结束位置
     * @author huangzihua
     * @date 2020-01-18
     */
    public static void quickSortStackSingleCycle(int[] array, int startIndex, int endIndex){
        //定义栈
        Stack<Map<String, Integer>> stack = new Stack<>();
        //使用HashMap存储起止位置
        Map<String, Integer> map = new HashMap<>();
        map.put("startIndex",startIndex);
        map.put("endIndex",endIndex);
        stack.push(map);    //入栈
        //判断栈是否为空，为空表示排序完成，结束循环；否则每一个元素代表一次分治排序，继续循环。
        while (!stack.isEmpty()){
            //取栈顶元素
            map = stack.pop();
            //调用分治排序
            int pivotIndex = partitionSingleCycle(array, map.get("startIndex"), map.get("endIndex"));
            if(pivotIndex-map.get("startIndex") > 1 ) {
                Map map1 = new HashMap();
                map1.put("startIndex",startIndex);
                map1.put("endIndex",pivotIndex-1);
                stack.push(map1);
            }
            if(map.get("endIndex")-pivotIndex > 1){
                Map map2 = new HashMap();
                map2.put("startIndex",pivotIndex+1);
                map2.put("endIndex",endIndex);
                stack.push(map2);
            }
        }
    }

    /**
     * 递归实现快速排序（单边循环）
     * @param array 数列
     * @param startIndex 开始边界
     * @param endIndex 结束边界
     * @author huangzihua
     * @date 2020-01-17
     */
    public static void quickSortSingleCycle(int[] array, int startIndex, int endIndex){
        if (startIndex >= endIndex) {
            return ;
        }
        int pivotIndex = partitionSingleCycle(array,startIndex,endIndex);
        quickSortSingleCycle(array,startIndex,pivotIndex-1);
        quickSortSingleCycle(array,pivotIndex+1,endIndex);
    }

    /**
     * 递归实现快速排序（双边循环）
     * @param array 数列
     * @param startIndex 开始边界
     * @param endIndex 结束边界
     * @author huangzihua
     * @date 2020-01-17
     */
    public static void quickSort(int[] array, int startIndex, int endIndex){
        if (startIndex >= endIndex) {
            return ;
        }
        // 取第一个元素作为基准元素。
        // 实际情况应该从数列中随机选取一个元素，然后和第一个元素交换位置。
        // 这样可降低选中数列最大值或最小值作为基准元素的概率。
        int pivot = array[startIndex];
        int left = startIndex;          //记录数列从左到右第一个比基准元素大的元素下标
        int right = endIndex;           //记录数列从右到左第一个比基准元素小的元素下标
        while (left < right) {
            while (left < right && array[right] > pivot) {  //先从右往左遍历，得到第一个比基准元素小的元素下标
                right--;
            }
            while (left < right && array[left] <= pivot) {  //再从左往右遍历，得到第一个比基准元素大的元素下标
                left++;
            }
            // 交换上述得到的两个值，比基准元素大的元素放到数列右端，小的则放到数列左端
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        // 最后left == right，此时将基准元素（位于数列第一个）和 left停留位置的元素进行交换。
        array[startIndex] = array[left];
        array[left] = pivot;

        // 递归
        quickSort(array,startIndex,left-1);
        quickSort(array,left+1,endIndex);
    }

    /**
     * 分治，单边循环
     * @param array 数列
     * @param startIndex 起始位置
     * @param endIndex 结束位置
     * @return 基准元素的位置
     * @author huangzihua
     * @date 2020-01-18
     */
    private static int partitionSingleCycle(int[] array, int startIndex, int endIndex){
        int pivotIndex = startIndex;    //基准元素位置
        int pivot = array[pivotIndex];  //基准元素
        for (int i = startIndex+1; i < endIndex; i++){
            if(array[i] < pivot){
                pivotIndex++;
                int temp = array[pivotIndex];
                array[pivotIndex] = array[i];
                array[i] = temp;
            }
        }
        array[startIndex] = array[pivotIndex];
        array[pivotIndex] = pivot;
        return pivotIndex;
    }
}
