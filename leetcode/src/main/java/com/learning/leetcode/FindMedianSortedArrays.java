package com.learning.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 */
public class FindMedianSortedArrays {

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int i=0,j=0;
        int n = (nums1.length+nums2.length)/2;
        while (i < nums1.length && j < nums2.length) {
            if(list.size() > n){
                break;
            }
            if(nums1[i] > nums2[j]){
                list.add(nums2[j]);
                j++;
            } else {
                list.add(nums1[i]);
                i++;
            }
        }
        while (i < nums1.length){
            if(list.size() > n){
                break;
            }
            list.add(nums1[i]);
            i++;
        }
        while (j < nums2.length){
            if(list.size() > n){
                break;
            }
            list.add(nums2[j]);
            j++;
        }
        if((nums1.length+nums2.length)%2==1){
            return list.get(n);
        } else {
            return (list.get(n)+list.get(n-1))*1.0/2;
        }
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        int i=0,j=0;
        while (i < nums1.length && j < nums2.length) {
            if(nums1[i] > nums2[j]){
                list.add(nums2[j]);
                j++;
            } else {
                list.add(nums1[i]);
                i++;
            }
        }
        while (i < nums1.length){
            list.add(nums1[i]);
            i++;
        }
        while (j < nums2.length){
            list.add(nums2[j]);
            j++;
        }
        if(list.size()%2==0){
            return (list.get(list.size()/2)+list.get(list.size()/2-1))*1.0/2;
        } else {
            return list.get(list.size()/2);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,3};
        int[] nums2 = new int[]{2};
        System.out.println(findMedianSortedArrays2(nums1,nums2));
    }
}
