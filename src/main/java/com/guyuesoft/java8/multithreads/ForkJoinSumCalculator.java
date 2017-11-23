package com.guyuesoft.java8.multithreads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

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
		if(length<=THRESHOLD) {
			return computeSequentially();
		}
		int splitPoint = start+length/2;
		ForkJoinSumCalculator leftTask=new ForkJoinSumCalculator(numbers, start, splitPoint);
		leftTask.fork();
		ForkJoinSumCalculator rightTask= new ForkJoinSumCalculator(numbers, splitPoint, end);
		Long rightResult= rightTask.compute();
		Long leftResult= leftTask.join();
		return leftResult+rightResult;
	}
	private long computeSequentially() {
		long sum=0;
		for(int i=start;i<end;i++) {
			sum+=numbers[i];
		}
		return sum;
	}
	
	public static long forkJoinSum(long n) {
		long[] numbers= LongStream.rangeClosed(1, n).toArray();
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
