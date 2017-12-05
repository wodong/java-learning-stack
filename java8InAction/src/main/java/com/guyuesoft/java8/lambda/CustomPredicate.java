package com.guyuesoft.java8.lambda;

/**
 * 
 * @author wodong
 *
 * @param <T>
 */
public interface CustomPredicate<T> {
	//һ��ֻ��һ��������������default�������Ľӿڿ�������lambda���ʽ
	boolean test(T t);
	
	// JAVA8�еķ������԰���ʵ�֣���������API������
	// �˷���ֻ��չʾdefault���������ã�����ʹ��
	default void println(String log) {
		System.out.println(log);
	}
}
