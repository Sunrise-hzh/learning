package com.learning.leetcode;

import java.util.*;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 */
public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        List<Character> list = new ArrayList<>();

        for (int i = 0; i<s.length(); i++){
            list.add(s.charAt(i));
            if(list.contains(s.charAt(i))){
                
            }
        }
        return "";
    }
    public static boolean check(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++){
            if (s.length()%2!=0 && i == s.length()/2){
                continue;
            }
            if(stack.contains(s.charAt(i))){
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        if (stack.size()==0){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(s+": "+check(s));
        System.out.println("aba: "+check("aba"));
        System.out.println("sbc: "+check("sbc"));
        System.out.println("a: "+check("a"));
        System.out.println("hello: "+check("hello"));
        System.out.println("helloolleh: "+check("helloolleh"));
        System.out.println("hellolleh: "+check("hellolleh"));
    }
}
