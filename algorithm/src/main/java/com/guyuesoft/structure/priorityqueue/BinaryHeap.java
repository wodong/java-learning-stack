package com.guyuesoft.structure.priorityqueue;

import java.util.Arrays;

/**
 * @ADT abstract data type ������������
 * @PriorityQueue ���ȶ���
 * 
 * @KeyMethod :
 *   insert(enqueue) , deleteMax(extractMax,dequeue)
 *   
 * @�������ʵ�ֺ����㷨��2-�ѣ� : 
 *   1. ���Ϲ��� : ���ڲ��뷽������Ԫ�ط������ͨ���ϸ��ķ�ʽȷ����λ��
 *   2. ���¹��� : ����ɾ�����ֵ��������С�
 *   	1��ɾ�����ֵ�󣬽����һ���ڵ����ڶѶ���ͨ���������ӽڵ�����ֵ�Ƚϣ������³���ȷ����λ��
 *   	2�����ڶ����򣬴ӵ�n/2���ڵ㵽���ڵ㣬�������¹���ÿ��Ԫ�أ����õ��ѡ�
 *   
 * @TODO Ӧ����չ
 * 
 *   1. d-��  ÿ���ڵ���d������
 *   2. �����С��
 *   3. �����ѵĺϲ�(merge)
 *   4. ��ʽ��(leftist heap)
 *   5. б�ѣ�skew heap)
 *   6. �������(binomial queue)
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

	private static final int DEFAULT_CAPACITY = 10;

	private int currentSize;

	private AnyType[] array;

	public BinaryHeap() {
		currentSize = 0;
		array = (AnyType[]) new Comparable[DEFAULT_CAPACITY];
	}

	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = (AnyType[]) new Comparable[capacity];
	}

	/**
	 * �ݹ鷽ʽ��ʼ��
	 * 
	 * @param items
	 */
	public BinaryHeap(AnyType[] items) {
		this(items, true);
	}

	/**
	 * ���õݹ鷽ʽ���ߵ�����ѭ���Ӷ�̬��������ʽ��ʼ��
	 * 
	 * @param items
	 * @param useRecurse
	 */
	public BinaryHeap(AnyType[] items, boolean useRecurse) {
		currentSize = items.length;
		array = (AnyType[]) new Comparable[items.length + DEFAULT_CAPACITY];
		int i = 1;
		for (AnyType item : items) {
			array[i++] = item;
		}
		if (useRecurse) {
			for (i = currentSize / 2; i > 0; i--) {
				percolateDownByRecurse(i);
			}
		} else {
			for (i = currentSize / 2; i > 0; i--) {
				percolateDown(i);
			}
		}
	}

	/**
	 * ���õݹ鷽ʽ
	 * 
	 * @param hole
	 */
	private void percolateDownByRecurse(int hole) {
		int childLeft = hole * 2;
		if (childLeft > currentSize) {
			return;
		}
		// �Ⱥ���ڵ�ȣ��ٺ��ҽڵ�ȣ�һ��ʵ������ȡ��ڵ㣬�ҽڵ������ڵ�����ֵ��������ڵ㣬���ҽڵ������±�
		// ���õ��������ֽ���ڵ���ҽڵ�Ƚϣ�Ȼ��ѡȡ�ϴ�ĺ�����ڵ�Ƚϡ�����ֻ�ݹ�һ����֧�Ϳ����ˣ�������ѭ��ʵ�֣���percolateDown��ʵ��
		if (array[hole].compareTo(array[childLeft]) < 0) {
			AnyType temp = array[hole];
			array[hole] = array[childLeft];
			array[childLeft] = temp;
			percolateDownByRecurse(childLeft);
		}
		//
		int childRight = childLeft + 1;
		if (childRight > currentSize) {
			return;
		}
		if (array[hole].compareTo(array[childRight]) < 0) {
			AnyType temp = array[hole];
			array[hole] = array[childRight];
			array[childRight] = temp;
			percolateDownByRecurse(childRight);
		}

	}

	/**
	 * �����Ž⣩ ���õ�����ѭ���Ӷ�̬��������ʽ(����)
	 * 
	 * @param hole
	 */
	private void percolateDown(int hole) {
		while (hole*2 <= currentSize) {
			int child = hole * 2;
			// ȡ���ҽڵ�����ڵ������
			if (child != currentSize && array[child + 1].compareTo(array[child]) > 0) {
				child++;
			}
			// �����³���ʽȷ��ÿ��Ԫ��λ��
			if (array[hole].compareTo(array[child]) < 0) {
				AnyType temp = array[hole];
				array[hole] = array[child];
				array[child] = temp;
				hole = child;
			} else {
				break;
			}
		}
	}

	public void insert(AnyType[] items) {
		for (AnyType item : items) {
			insert(item);
		}
	}

	/**
	 * �����Ž⣩���õ�����ѭ���Ӷ�̬�������ķ�ʽʵ�ֲ���
	 * 
	 * @param x
	 */
	public void insert(AnyType x) {
		// �Զ�����
		autoEnlargeArray();
		// ���²���Ԫ�ط����������ֻ���õ��¼�������һ��λ�ã������λ������ɶ�����û��ʵ�ʷ����Ԫ�أ�
		int hole = ++currentSize;
		// ���ѭ�����߼���ÿ�κ͸��ڵ�Ƚϣ�����ȸ��ڵ�󣬽����ڵ���붴�У������ϸ������ڵ�λ�ã�Ȼ�����ѭ����ֱ�����ڵ��˳�
		// ���ѭ���Ĳ���Ϊ��̬�� hole/2
		while (hole > 1) {
			int parent = hole / 2;
			if (x.compareTo(array[parent]) > 0) {
				array[hole] = array[parent];
				hole = parent;
			} else {
				break;
			}
		}
		// ��󽫴�����Ԫ�ز���ѡ�õĶ���
		array[hole] = x;
	}

	/**
	 * ���õݹ����ʽʵ�ֲ���
	 * 
	 * @param x
	 */
	public void insertByRecurse(AnyType x) {
		autoEnlargeArray();
		array[++currentSize] = x;
		percolateUp(currentSize);
	}

	/**
	 * ������Ԫ�ر��ֻ��ϸ�������λ��(����)
	 * @param index
	 */
	private void percolateUp(int index) {
		int parent = index / 2;
		if (parent == 0) {
			return;
		}
		if (array[index].compareTo(array[parent]) > 0) {
			AnyType temp = array[index];
			array[index] = array[parent];
			array[parent] = temp;
			percolateUp(parent);
		}
	}

	public AnyType findMax() {
		return array[1];
	}

	// extract max
	public AnyType deleteMax() {
		if(isEmpty()) {
			return null;
		}
		//������ڵ�Ԫ��
		AnyType max= findMax();
		//�����һ��Ԫ�����ڸ��ڵ㣬����size��һ
		array[1]=array[currentSize--];
		//
		percolateDown(1);
		return max;
	}

	public boolean isEmpty() {
		return currentSize==0;
	}

	public void makeEmpty() {
		currentSize=0;
		array=null;
	}

	private void autoEnlargeArray() {
		if (currentSize >= array.length + 1) {
			AnyType[] temp = (AnyType[]) new Comparable[array.length * 2 + 1];
			for (int i = 1; i < array.length; i++) {
				temp[i] = array[i];
			}
			array = temp;
		}
	}
	/*
	 * ����ĳ�ڵ����ȼ�
	 * ����ƣ������ο�����AnyTypeΪIntegerʱ��Ч
	 */
	public void decreaseKey(int index,int value){
		if(index>currentSize) {
			return ;
		}
		if(array[index] instanceof Integer) {
			Integer current = (Integer) array[index];
			Integer now= current-value;
			array[index]= (AnyType) now;
		}
		percolateDown(index);
	}
	
	/*
	 * ����ĳ�ڵ����ȼ�
	 * ����ƣ������ο�����AnyTypeΪIntegerʱ��Ч
	 */
	public void increaseKey(int index,int value){
		if(index>currentSize) {
			return ;
		}
		if(array[index] instanceof Integer) {
			Integer current = (Integer) array[index];
			Integer now= current+value;
			array[index]= (AnyType) now;
		}
		percolateUp(index);
	}
	
	/**
	 * ɾ��ĳ�ڵ�
	 * ����ƣ������ο�����AnyTypeΪIntegerʱ��Ч
	 * @param index
	 */
	public void delete(int index) {
		increaseKey(index,Integer.MAX_VALUE/2);
		deleteMax();
	}

	@Override
	public String toString() {
		String value="[";
		for(int i=1;i<=currentSize;i++) {
			if(i!=1) {
				value+=", ";
			}
			value+= array[i];
		}
		value+="]";
		return value;
	}

}
