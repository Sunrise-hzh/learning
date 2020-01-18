package com.learning.algorithms.tree;

import java.util.Arrays;

/**
 * 优先队列，最大值在顶端
 */
public class MyPriorityQueue {
    private int[] array;
    private int size;
    public MyPriorityQueue(){
        this.array = new int[32];
        this.size = 0;
    }

    /**
     * 上浮操作
     * @author huangzihua
     * @date 2020-01-15
     */
    public void upAdjust(){
        if(size == 0){
            throw new RuntimeException("队列为空");
        }
        int childIndex = size-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];
        while (childIndex>0 && array[parentIndex]<temp){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex-1)/2;
        }
        array[childIndex] = temp;
    }

    /**
     * 下沉
     * @author huangzihua
     * @date 2020-01-15
     */
    public void downAdjust(){
        if(size == 0){
            throw new RuntimeException("队列为空");
        }
        int parentIndex = 0;
        int childIndex = 1;
        int temp = array[parentIndex];
        while (childIndex < size){
            if (childIndex+1 < size && array[childIndex+1] > array[childIndex]){
                childIndex++;
            }
            if(temp >= array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex*2+1;
        }
        array[parentIndex] = temp;
    }

    /**
     * 扩容队列，容量*2
     * @author huangzihua
     * @date 2020-01-15
     */
    public void enlargeCapacity(){
        this.array = Arrays.copyOf(this.array, size*2); //拷贝数组
    }

    /**
     * 入队
     * @param element 待入队的元素值
     * @author huangzihua
     * @date 2020-01-15
     */
    public void enqueue(int element){
        if(size == array.length){
            enlargeCapacity();
        }
        array[size] = element;
        size++;
        upAdjust();
    }

    /**
     * 出队
     * @return 出队元素
     * @author huangzihua
     * @date 2020-01-15
     */
    public int dequeue(){
        if (size <= 0){
            throw new RuntimeException("队列为空");
        }
        int element = array[0];     //取要出队列的元素
        array[0] = array[size-1];   //将最后一个元素替代第一个元素
        size--;     //数组实际大小减1
        downAdjust();   //下沉调整队列
        return element;
    }

    public static void main(String[] args) throws Exception {
        MyPriorityQueue priorityQueue = new MyPriorityQueue();
        priorityQueue.enqueue(3);
        priorityQueue.enqueue(5);
        priorityQueue.enqueue(10);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(7);
        System.out.println(" 出队元素：" + priorityQueue.dequeue());
        System.out.println(" 出队元素：" + priorityQueue.dequeue());
    }
}
