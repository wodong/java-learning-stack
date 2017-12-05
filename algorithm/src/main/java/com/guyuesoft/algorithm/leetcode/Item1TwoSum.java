package com.guyuesoft.algorithm.leetcode;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import sun.dc.DuctusRenderingEngine;

/**
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * Example:
 * 
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * 
 * return [0, 1].
 *
 * 给定一个数字数组，如果其中两个元素之和等于给定值，则返回由下标组成的数组。
 * 
 * 你可以假设每个输入只有唯一一个解决方案，你不能使用用一个元素超过一次.
 * 
 * 例如:
 * 
 * 给出 nums = [2, 7, 11, 15], target = 9,
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9,
 * 
 * 返回 [0, 1].
 * 
 * @author xiaodong
 *
 */
public class Item1TwoSum {
	public static void main(String[] args) {
		int[] array= IntStream.range(1, 100000).toArray();
		int target=100000+9999;
		long before1 = System.nanoTime();
		int[] result1= twoSum1(array,target);
		long d1  = System.nanoTime()-before1;
		System.out.println("Result from method1 : "+Arrays.toString(result1)+", Time cost : "+d1);
		long before2 = System.nanoTime();
		int[] result2= twoSum2(array,target);
		long d2  = System.nanoTime()-before2;
		System.out.println("Result from method2 : "+Arrays.toString(result2)+", Time cost : "+d2);
		long before3 = System.nanoTime();
		int[] result3= twoSum2(array,target);
		long d3  = System.nanoTime()-before3;
		System.out.println("Result from method3 : "+Arrays.toString(result3)+", Time cost : "+d3);
	}

	/*
	 * 先解决问题，不考虑使用一次限制，两层遍历，求出结果
	 */
	public static int[] twoSum1(int[] source, int target) {
		for(int i=0;i<source.length;i++) {
			for(int j=i;j<source.length;j++) {
				if(source[j]== target-source[i]) {
					return new int[] {i,j};
				}
			}
		}
		return null;
	}
	/*
	 * 一次遍历，在hashMap中查找目标值，找不到则插入hashMap
	 */
	public static int[] twoSum2(int[] source, int target) {
		Map<Integer,Integer> store= new HashMap<>();
		for(int i=0;i<source.length;i++) {
			store.put(source[i], i);
		}
		for(int i=0;i<source.length;i++) {
			int result= target-source[i];
			if(store.containsKey(result)) {
				return new int[] {i,store.get(result)};
			}
		}
		
		return null;
	}
	
	/*
	 * 一次遍历，第一次构建hashMap，第二次在hashMap中查找目标值
	 */
	public static int[] twoSum3(int[] source, int target) {
		Map<Integer,Integer> store= new HashMap<>();
		for(int i=0;i<source.length;i++) {
			int result= target-source[i];
			if(store.containsKey(result)) {
				return new int[] {i,store.get(result)};
			}else {
				store.put(source[i], i);
			}
		}
		
		return null;
	}

}
