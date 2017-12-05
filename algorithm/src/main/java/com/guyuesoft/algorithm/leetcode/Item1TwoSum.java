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
 * ����һ���������飬�����������Ԫ��֮�͵��ڸ���ֵ���򷵻����±���ɵ����顣
 * 
 * ����Լ���ÿ������ֻ��Ψһһ������������㲻��ʹ����һ��Ԫ�س���һ��.
 * 
 * ����:
 * 
 * ���� nums = [2, 7, 11, 15], target = 9,
 *
 * ��Ϊ nums[0] + nums[1] = 2 + 7 = 9,
 * 
 * ���� [0, 1].
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
	 * �Ƚ�����⣬������ʹ��һ�����ƣ����������������
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
	 * һ�α�������hashMap�в���Ŀ��ֵ���Ҳ��������hashMap
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
	 * һ�α�������һ�ι���hashMap���ڶ�����hashMap�в���Ŀ��ֵ
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
