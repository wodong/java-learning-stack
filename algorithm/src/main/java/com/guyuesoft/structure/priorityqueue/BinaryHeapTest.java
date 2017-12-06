package com.guyuesoft.structure.priorityqueue;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

import com.sun.tools.javac.util.List;

public class BinaryHeapTest {

	public static void main(String[] args) {
		BinaryHeapTest test = new BinaryHeapTest();
		log("----------测试插入方法开始----------");
		test.testinsert();
		log("----------测试插入方法结束----------");
		log("----------测试数据初始化方法开始----------");
		test.testInitialize();
		log("----------测试数据初始化方法结束----------");
		log("----------测试提取并删除最大节点方法开始----------");
		test.testDeleteMax();
		log("----------测试提取并删除最大节点方法结束----------");
		log("----------测试调整优先级相关方法开始----------");
		test.testChangeKey();
		log("----------测试调整优先级相关方法结束----------");
		log("----------测试Java自带优先队列PriorityQueue开始----------");
		test.testJavaDefault();
		log("----------测试Java自带优先队列PriorityQueue结束----------");
	}



	public void testinsert() {
		BinaryHeap<Integer> queue1 = new BinaryHeap<>();
		BinaryHeap<Integer> queue2 = new BinaryHeap<>();
		int[] data = { 10, 3, 5, 12, 44, 9 };
		for (int value : data) {
			queue1.insert(value);
			queue2.insertByRecurse(value);
		}
		log("循环加动态步长的插入算法 : " + queue1);
		log("递归的插入算法 : " + queue2);
	}

	public void testInitialize() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		BinaryHeap<Integer> queue1 = new BinaryHeap<>(data, false);
		BinaryHeap<Integer> queue2 = new BinaryHeap<>(data, true);
		BinaryHeap<Integer> queue3 = new BinaryHeap<>();
		queue3.insert(data);
		log("循环加动态步长方式，先填充数组，然后将 size/2 ~ 1 的顶点元素逐一过滤下沉 : " + queue1);
		log("递归方式，先填充数组，然后将 size/2 ~ 1 的顶点元素逐一过滤下沉 : " + queue2);
		log("先置空数组，然后逐个插入 : " + queue3);
	}

	public void testDeleteMax() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		BinaryHeap<Integer> queue = new BinaryHeap<>(data, false);
		log("初始堆 : " + queue);
		while (!queue.isEmpty()) {
			int item = queue.deleteMax();
			log("当前删除的最大值 : " + item + " , 剩余堆 : " + queue);
		}
	}
	
	public void testJavaDefault() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		//此默认为最小堆
		PriorityQueue<Integer> queue= new PriorityQueue();
		queue.addAll(List.from(data));
		while (!queue.isEmpty()) {
			int item = queue.poll();
			log("当前删除的最小值 : " + item + " , 剩余堆 : " + queue);
		}
		
	}
	public  void testChangeKey() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		BinaryHeap<Integer> queue = new BinaryHeap<>(data, false);
		log("初始堆 : " + queue);
		//提高最后一个元素优先级 
		queue.increaseKey(6, 100);
		log("将最后元素(5)优先级增加100 : " + queue);
		queue.decreaseKey(3, 100);
		log("将第三个元素(44)优先级降低100 : " + queue);
		queue.delete(2);
		log("删除第三个元素(12) : " + queue);
	}

	public static void log(String message) {
		System.out.println(message);
	}
}
