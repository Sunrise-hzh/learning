package com.learning.algorithms.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class MyBinaryTree {

    /**
     * 构建二叉树
     * @param dataList 输入序列
     * @return 二叉树结点
     * @author huangzihua
     * @date 2020-01-08
     */
    public static TreeNode create(LinkedList<Integer> dataList){
        if (dataList == null || dataList.isEmpty()){
            return null;
        }
        Integer data = dataList.removeFirst();
        TreeNode treeNode = null;
        if(data != null){
            treeNode = new TreeNode(data);
            treeNode.leftChild = create(dataList);
            treeNode.rightChild = create(dataList);
        }
        return treeNode;
    }

    /**
     * 前序遍历(递归)
     * @param node 树结点
     * @author huangzihua
     * @date 2020-01-08
     */
    public static void preOrderTraversal(TreeNode node){
        if(node == null){
            System.out.println("null");
        } else {
            System.out.println(node.data);
            preOrderTraversal(node.leftChild);
            preOrderTraversal(node.rightChild);
        }
    }

    /**
     * 中序遍历(递归)
     * @param node 树结点
     * @author huangzihua
     * @date 2020-01-08
     */
    public static void inOrderTraversal(TreeNode node){
        if(node == null){
            System.out.println("null");
        } else {
            inOrderTraversal(node.leftChild);
            System.out.println(node.data);
            inOrderTraversal(node.rightChild);
        }
    }

    /**
     * 后序遍历(递归)
     * @param node 树结点
     * @author huangzihua
     * @date 2020-01-08
     */
    public static void postOrderTraversal(TreeNode node){
        if(node == null){
            System.out.println("null");
        } else {
            postOrderTraversal(node.leftChild);
            postOrderTraversal(node.rightChild);
            System.out.println(node.data);
        }
    }

    /**
     * 前序遍历（非递归）
     * @param root 二叉树根节点
     * @author huangzihua
     * @date 2020-01-08
     */
    public static void preOrderTraversalStack(TreeNode root){
        //定义栈
        Stack<TreeNode> nodeStack = new Stack<>();
        //结点索引，表示当前结点
        TreeNode node = root;
        while (node != null){
            System.out.println(node.data);  //先序输出
            if(node.leftChild != null){ //若存在左结点，则将当前结点入栈，同时遍历将左结点置为当前结点
                nodeStack.push(node);
                node = node.leftChild;
            } else if (node.rightChild != null){    //若存在右结点，则直接将右结点置为当前结点，不入栈
                node = node.rightChild;
            } else if (!nodeStack.isEmpty()){   //若左右结点都不存在，且栈不为空，则获取栈顶元素的右结点
                node = nodeStack.pop().rightChild;
            } else {    //以上都不满足，说明遍历结束
                node = null;
            }
        }
    }

    /**
     * 树结点
     * @author huangzihua
     * @date 2020-01-08
     */
    private static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        public TreeNode(int data){
            this.data = data;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.
                asList(new Integer[]{3,2,9,null,null,10,null,
                        null,8,null,4,null,7}));
        TreeNode treeNode = create(inputList);
        System.out.println(" 前序遍历：");
        preOrderTraversal(treeNode);
        System.out.println(" 前序遍历(非递归)：");
        preOrderTraversalStack(treeNode);
        System.out.println(" 中序遍历：");
        inOrderTraversal(treeNode);
        System.out.println(" 后序遍历：");
        postOrderTraversal(treeNode);
    }
}
