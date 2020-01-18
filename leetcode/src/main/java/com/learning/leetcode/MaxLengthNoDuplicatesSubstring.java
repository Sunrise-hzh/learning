package com.learning.leetcode;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class MaxLengthNoDuplicatesSubstring {

    public static void main(String[] args) {
        String s = "7a";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * 首次编写的算法，由于字符串String的contains底层是双循环判断，所以当前算法耗时较长，O(n^3)。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s){
        if(s == null){
            return 0;
        }
        int length = 0;
        String total = "";
        for (int i=0; i < s.length(); i++){
            String c = s.substring(i,i+1);
            if(total.contains(c)) {
                total = total.substring(total.indexOf(c)+1);
            }
            total += c;
            length = Math.max(length,total.length());
        }

        return length;
    }

    /**
     * 参考leetcode官方解答的滑动窗口优化算法，使用 HashMap 存放key-value，记录字符及其位置。
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s){
        if(s == null){
            return 0;
        }

        int length = 0;
        int i = 0,j = 0;

        Map<Character,Integer> map = new HashMap<>();
        for (j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            if(map.containsKey(c)) {
                i = Math.max(i,map.get(c)+1);
            }
            map.put(c,j);
            length = Math.max(length,j-i+1);
        }

        return length;
    }


}
