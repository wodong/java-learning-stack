package com.guyuesoft.structure.priorityqueue;

import java.util.Arrays;

/**
 * @ADT abstract data type 抽象数据类型
 * @PriorityQueue 优先队列
 * 
 * @KeyMethod :
 *   insert(enqueue) , deleteMax(extractMax,dequeue)
 *   
 * @最大二叉堆实现核心算法（2-堆） : 
 *   1. 向上过滤 : 用在插入方法。将元素放在最后，通过上浮的方式确定其位置
 *   2. 向下过滤 : 用在删除最大值或堆排序中。
 *   	1）删除最大值后，将最后一个节点置于堆顶，通过和两个子节点的最大值比较，依次下沉，确定其位置
 *   	2）对于堆排序，从第n/2个节点到根节点，依次向下过滤每个元素，最后得到堆。
 *   
 * @TODO 应用扩展
 * 
 *   1. d-堆  每个节点有d个孩子
 *   2. 最大最小堆
 *   3. 两个堆的合并(merge)
 *   4. 左式堆(leftist heap)
 *   5. 斜堆（skew heap)
 *   6. 二项队列(binomial queue)
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
	 * 递归方式初始化
	 * 
	 * @param items
	 */
	public BinaryHeap(AnyType[] items) {
		this(items, true);
	}

	/**
	 * 采用递归方式或者迭代（循环加动态步长）方式初始化
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
	 * 采用递归方式
	 * 
	 * @param hole
	 */
	private void percolateDownByRecurse(int hole) {
		int childLeft = hole * 2;
		if (childLeft > currentSize) {
			return;
		}
		// 先和左节点比，再和右节点比，一下实际上是取左节点，右节点和自身节点得最大值放入自身节点，左右节点再往下比
		// 更好的做法是现将左节点和右节点比较，然后选取较大的和自身节点比较。这样只递归一个分支就可以了，可以用循环实现，见percolateDown的实现
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
	 * （最优解） 采用迭代（循环加动态步长）方式(下滤)
	 * 
	 * @param hole
	 */
	private void percolateDown(int hole) {
		while (hole*2 <= currentSize) {
			int child = hole * 2;
			// 取左右节点的最大节点的索引
			if (child != currentSize && array[child + 1].compareTo(array[child]) > 0) {
				child++;
			}
			// 过滤下沉方式确定每个元素位置
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
	 * （最优解）采用迭代（循环加动态步长）的方式实现插入
	 * 
	 * @param x
	 */
	public void insert(AnyType x) {
		// 自动增长
		autoEnlargeArray();
		// 将新插入元素放入最后（这里只是拿到新加入的最后一个位置，把这个位置想象成洞，并没有实际放入该元素）
		int hole = ++currentSize;
		// 这个循环的逻辑是每次和父节点比较，如果比父节点大，将父节点放入洞中，将洞上浮到父节点位置，然后继续循环，直到根节点退出
		// 这个循环的步长为动态的 hole/2
		while (hole > 1) {
			int parent = hole / 2;
			if (x.compareTo(array[parent]) > 0) {
				array[hole] = array[parent];
				hole = parent;
			} else {
				break;
			}
		}
		// 最后将待插入元素插入选好的洞中
		array[hole] = x;
	}

	/**
	 * 采用递归的形式实现插入
	 * 
	 * @param x
	 */
	public void insertByRecurse(AnyType x) {
		autoEnlargeArray();
		array[++currentSize] = x;
		percolateUp(currentSize);
	}

	/**
	 * 将插入元素保持或上浮到合适位置(上滤)
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
		//提出根节点元素
		AnyType max= findMax();
		//将最后一个元素置于根节点，并将size减一
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
	 * 降低某节点优先级
	 * 简单设计，仅供参考，当AnyType为Integer时有效
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
	 * 增加某节点优先级
	 * 简单设计，仅供参考，当AnyType为Integer时有效
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
	 * 删除某节点
	 * 简单设计，仅供参考，当AnyType为Integer时有效
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
