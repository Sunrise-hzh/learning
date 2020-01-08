package com.learning.algorithms.stack;

import java.util.Stack;

public class MyStack {
    private int[] array;
    private int size;
    public MyStack(int capacity){
        this.array = new int[capacity];
        this.size = 0;
    }

    /**
     * 进栈
     * @param element 新增的元素
     * @author huangzihua
     * @date 2020-01-08
     */
    public void push(int element){
        if (size == array.length) {
            throw new RuntimeException("栈满");
        }
        array[size] = element;
        size++;
    }

    /**
     * 出栈
     * @return 出栈的元素
     * @author huangzihua
     * @date 2020-01-08
     */
    public int pop(){
        if(size == 0){
            throw new RuntimeException("栈空");
        } else {
            int temp = array[size-1];
            size--;
            return temp;
        }
    }

    /**
     * 判断栈是否为空，返回true为空
     * @author huangzihua
     * @date 2020-01-08
     */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack(10);
        myStack.push(0);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);
        myStack.push(7);
        myStack.push(8);
        myStack.push(9);

        while (!myStack.isEmpty()){
            System.out.println(myStack.pop());
        }
    }
}
