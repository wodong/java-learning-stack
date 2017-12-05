package com.guyuesoft.java8.multithreads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

//这实际是JAVA7的新特性
public class ForkJoinSumCalculator extends java.util.concurrent.RecursiveTask<Long> {

	private final long[] numbers;
	private final int start;
	private final int end;
	
	public static final long THRESHOLD=10;
	
	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers,0,numbers.length);
	}
	public ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers=numbers;
		this.start= start;
		this.end=end;
	}
	@Override
	protected Long compute() {
		int length = end-start;
		//到达需处理的最小单元，进行计算
		if(length<=THRESHOLD) {
			return computeSequentially();
		}
		int splitPoint = start+length/2;
		//将任务一分为二，左分支做一半，又分支做一半
		ForkJoinSumCalculator leftTask=new ForkJoinSumCalculator(numbers, start, splitPoint);
		//将左分支fork出去
		leftTask.fork();
		ForkJoinSumCalculator rightTask= new ForkJoinSumCalculator(numbers, splitPoint, end);
		//右分支也可以fork出去，这里为了节省资源，共用了和父线程公用一个
		Long rightResult= rightTask.compute();
		//将左分支计算结果返回
		Long leftResult= leftTask.join();
		return leftResult+rightResult;
	}
	private long computeSequentially() {
		long sum=0;
		//求和统计计算
		for(int i=start;i<end;i++) {
			sum+=numbers[i];
		}
		return sum;
	}
	
	public static long forkJoinSum(long n) {
		// 生成一个顺序数组
		long[] numbers= LongStream.rangeClosed(1, n).toArray();
		// 利用ForkJoinPool线程池去调用Task
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}

	public static void main(String[] args) {
		Long result= forkJoinSum(100);
		System.out.println(result);
		Long result2= forkJoinSum(1000);
		System.out.println(result2);
		Long result3= forkJoinSum(10000);
		System.out.println(result3);
	}
}
