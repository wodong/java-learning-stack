package com.guyuesoft.java8.lambda;

/**
 * 
 * @author wodong
 *
 * @param <T>
 */
public interface CustomPredicate<T> {
	//一个只有一个方法（不包括default方法）的接口可以用做lambda表达式
	boolean test(T t);
	
	// JAVA8中的方法可以包含实现，方便类库的API升级。
	// 此方法只是展示default方法的作用，并无使用
	default void println(String log) {
		System.out.println(log);
	}
}
