package com.guyuesoft.structure.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * ��������ɢ�б�ʵ��
 * װ��������Ϊ1��
 * ��������
 * ����̽�ⷨ��ƽ��̽�ⷨ��˫ɢ��
 * @author xiaodong
 *
 * @param <AnyType>
 */
public class SeparateChainingHashTable<AnyType> {

	private static final int DEFAULT_TABLE_SIZE = 101;

	private List<AnyType>[] theLists;

	private int currentSize;

	public SeparateChainingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public SeparateChainingHashTable(int size) {
		theLists = new LinkedList[nextPrime(size)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<>();
		}
	}

	public void insert(AnyType x) {
		List<AnyType> whichList= theLists[myhash(x)];
		if(!whichList.contains(x)) {
			whichList.add(x);
			if(++currentSize>theLists.length) {
				rehash();
			}
		}
	}

	public void remove(AnyType x) {
		List<AnyType> whichList= theLists[myhash(x)];
		if(whichList.contains(x)) {
			whichList.remove(x);
			currentSize--;
		}
	}

	public boolean contains(AnyType x) {
		List<AnyType> whichList = theLists[myhash(x)];
		return whichList.contains(x);
	}

	public void makeEmpty() {
		for (int i = 0; i < theLists.length; i++) {
			theLists[i].clear();
			currentSize = 0;
		}
	}

	//

	private void rehash() {
		List<AnyType>[] oldList = theLists;
		theLists = new LinkedList[nextPrime(currentSize*2)];
		for (int i = 0; i < theLists.length; i++) {
			theLists[i] = new LinkedList<>();
		}
		for(List<AnyType> anyTypes:oldList) {
			for(AnyType any: anyTypes) {
				insert(any);
			}
		}
	}

	private int myhash(AnyType x) {
		int hashVal = x.hashCode();
		hashVal %= theLists.length;
		if (hashVal < 0) {
			hashVal += theLists.length;
		}
		return hashVal;
	}

	private static int nextPrime(int n) {
		while (!isPrime(n)) {
			n++;
		}
		return n;
	}

	private static boolean isPrime(int n) {
		return isPrime2(n);
	}
	
	/**
	 * �����������ܱ�1������������
	 * @param n
	 * @return
	 */
	private static boolean isPrime1(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	/**
	 * �� ����������ȡ��2��������ƽ����
	 * @param n
	 * @return
	 */
	private static boolean isPrime2(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * �� ������������2��������ƽ����������������ǰ������
	 * @param n
	 * @return
	 */
	private static boolean isPrime3(int n) {
		// 
		return false;
	}

}
