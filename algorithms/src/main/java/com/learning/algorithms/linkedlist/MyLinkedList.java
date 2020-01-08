package com.learning.algorithms.linkedlist;

public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    /**
     * 插入元素值到指定位置
     * @param data 元素值
     * @param index 下标位置
     * @author huangzihua
     * @date 2020-01-07
     */
    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("索引超出链表实际容量大小，无法插入");
        }
        if (size == 0) {
            head = new Node(data);
            tail = head;
        } else {
            Node newNode = new Node(data);
            if (index == 0) {
                newNode.next = head;
                head = newNode;
            } else if (index == size) {
                tail.next = newNode;
                tail = newNode;
            } else {
                Node currNode = this.getElement(index-1);
                newNode.next = currNode.next;
                currNode.next = newNode;
            }
        }
        size++;
    }

    /**
     * 删除指定位置的元素
     * @param index 删除元素的下标
     * @return 被删除的结点
     * @author huangzihua
     * @date 2020-01-08
     */
    public Node remove(int index){
        if (size == 0){
            throw new RuntimeException("链表为空");
        }
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException("超出链表结点范围");
        }
        Node prevNode = head;
        Node deleteNode = null;
        if(index == 0){
            deleteNode = head;
            head = head.next;
            deleteNode.next = null;
        } else if (index == size - 1) {
            prevNode = getElement(index-1);
            deleteNode = tail;
            prevNode.next = null;
            tail = prevNode;
        } else {
            prevNode = getElement(index-1);
            deleteNode = prevNode.next;
            prevNode.next = deleteNode.next;
            deleteNode.next = null;
        }
        size--;
        return deleteNode;
    }

    /**
     * 根据指定下标获取链表元素
     *
     * @param index 元素下标
     * @return 链表结点
     * @author huangzihua
     * @date 2020-01-07
     */
    public Node getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("超出链表大小范围");
        }
        Node currNode = head;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }
        return currNode;
    }

    /**
     * 打印所有链表元素
     * @author huangzihua
     * @date 2020-01-08
     */
    public void printAll(){
        if (size == 0){
            System.out.println("null");
        } else {
            Node currNode = head;
            System.out.print("{"+currNode.data);
            while (currNode.next != null){
                currNode = currNode.next;
                System.out.print("," + currNode.data);
            }
            System.out.println("}");
        }
    }

    /**
     * 结点类
     * @author huangzihua
     * @date 2020-01-07
     */
    private static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data){
            this.data = data;
        }

        Node(int data, Node next, Node prev) {
            this(data);
            this.next = next;
            this.prev = prev;
        }
    }


    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(1,0);
        myLinkedList.insert(2,1);
        myLinkedList.insert(3,2);
        myLinkedList.insert(4,3);
        myLinkedList.insert(5,4);
        myLinkedList.insert(6,5);
        myLinkedList.remove(4);
        myLinkedList.printAll();
    }
}