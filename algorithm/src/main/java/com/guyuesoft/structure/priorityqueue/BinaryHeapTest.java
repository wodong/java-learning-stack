package com.guyuesoft.structure.priorityqueue;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

import com.sun.tools.javac.util.List;

public class BinaryHeapTest {

	public static void main(String[] args) {
		BinaryHeapTest test = new BinaryHeapTest();
		log("----------���Բ��뷽����ʼ----------");
		test.testinsert();
		log("----------���Բ��뷽������----------");
		log("----------�������ݳ�ʼ��������ʼ----------");
		test.testInitialize();
		log("----------�������ݳ�ʼ����������----------");
		log("----------������ȡ��ɾ�����ڵ㷽����ʼ----------");
		test.testDeleteMax();
		log("----------������ȡ��ɾ�����ڵ㷽������----------");
		log("----------���Ե������ȼ���ط�����ʼ----------");
		test.testChangeKey();
		log("----------���Ե������ȼ���ط�������----------");
		log("----------����Java�Դ����ȶ���PriorityQueue��ʼ----------");
		test.testJavaDefault();
		log("----------����Java�Դ����ȶ���PriorityQueue����----------");
	}



	public void testinsert() {
		BinaryHeap<Integer> queue1 = new BinaryHeap<>();
		BinaryHeap<Integer> queue2 = new BinaryHeap<>();
		int[] data = { 10, 3, 5, 12, 44, 9 };
		for (int value : data) {
			queue1.insert(value);
			queue2.insertByRecurse(value);
		}
		log("ѭ���Ӷ�̬�����Ĳ����㷨 : " + queue1);
		log("�ݹ�Ĳ����㷨 : " + queue2);
	}

	public void testInitialize() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		BinaryHeap<Integer> queue1 = new BinaryHeap<>(data, false);
		BinaryHeap<Integer> queue2 = new BinaryHeap<>(data, true);
		BinaryHeap<Integer> queue3 = new BinaryHeap<>();
		queue3.insert(data);
		log("ѭ���Ӷ�̬������ʽ����������飬Ȼ�� size/2 ~ 1 �Ķ���Ԫ����һ�����³� : " + queue1);
		log("�ݹ鷽ʽ����������飬Ȼ�� size/2 ~ 1 �Ķ���Ԫ����һ�����³� : " + queue2);
		log("���ÿ����飬Ȼ��������� : " + queue3);
	}

	public void testDeleteMax() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		BinaryHeap<Integer> queue = new BinaryHeap<>(data, false);
		log("��ʼ�� : " + queue);
		while (!queue.isEmpty()) {
			int item = queue.deleteMax();
			log("��ǰɾ�������ֵ : " + item + " , ʣ��� : " + queue);
		}
	}
	
	public void testJavaDefault() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		//��Ĭ��Ϊ��С��
		PriorityQueue<Integer> queue= new PriorityQueue();
		queue.addAll(List.from(data));
		while (!queue.isEmpty()) {
			int item = queue.poll();
			log("��ǰɾ������Сֵ : " + item + " , ʣ��� : " + queue);
		}
		
	}
	public  void testChangeKey() {
		Integer[] data = { 10, 3, 5, 12, 44, 9 };
		BinaryHeap<Integer> queue = new BinaryHeap<>(data, false);
		log("��ʼ�� : " + queue);
		//������һ��Ԫ�����ȼ� 
		queue.increaseKey(6, 100);
		log("�����Ԫ��(5)���ȼ�����100 : " + queue);
		queue.decreaseKey(3, 100);
		log("��������Ԫ��(44)���ȼ�����100 : " + queue);
		queue.delete(2);
		log("ɾ��������Ԫ��(12) : " + queue);
	}

	public static void log(String message) {
		System.out.println(message);
	}
}
