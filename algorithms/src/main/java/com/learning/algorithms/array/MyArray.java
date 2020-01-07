package com.learning.algorithms.array;

/**
 * 自定义简单数组实现
 */
public class MyArray {
    private int[] array;
    private int size;
    public MyArray(int capacity){
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 插入操作
     * @param element 插入元素
     * @param index 插入的下标位置
     * @author huangzihua
     * @date 2020-01-07
     */
    public void insert(int element, int index){
        if(array.length == size){
            throw new RuntimeException("容量已满");
        }
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException("下标越界");
        }
        for (int i = size; i > index; i--){
            array[i] = array[i-1];
        }
        array[index] = element;
        size++;
    }

    /**
     * 打印数组
     * @author huangzihua
     * @date 2020-01-07
     */
    public void print(){
        if(size == 0){
            System.out.print("空");
        }
        System.out.print("{"+array[0]);
        for (int i=1; i < size; i++){
            System.out.print(","+array[i]);
        }
        System.out.println("}");
    }

    /**
     * 删除数组元素
     * @param index 需删除的元素下标
     * @author huangzihua
     * @date 2020-01-07
     */
    public int remove(int index){
        if(size <= 0){
            throw new RuntimeException("当前数组为空，没有可删除元素");
        }
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("下标越界");
        }
        int deletedElement = array[index];
        for (int i = index; i < size-1; i++){
            array[i] = array[i+1];
        }
//        array[size-1] = 0;
        size--;
        return deletedElement;
    }

    public static void main(String[] args) {
        MyArray myArray = new MyArray(10);
        myArray.insert(1,0);
        myArray.insert(2,0);
        myArray.insert(3,0);
        myArray.insert(4,0);
        myArray.insert(5,0);
        myArray.remove(0);
        myArray.print();
    }
}
