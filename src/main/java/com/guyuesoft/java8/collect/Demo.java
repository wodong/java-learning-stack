package com.guyuesoft.java8.collect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

	public static void main(String[] args) {
		List<Person> source = Arrays.asList(new Person("Li Lei", 90,true), new Person("Li Lei", 98,true),
				new Person("Han Meimei", 60,false), new Person("Xiao wang", 82,false), new Person("Xiao zhang", 50,false),new Person("Xiao li", 94,false));
		//ԭʼ���ݴ�ӡ
		System.out.println("ԭʼ���ݴ�ӡ : ");
		source.stream().forEach(System.out::println);
		//��������
		long totalSize= source.stream().collect(Collectors.counting());
		System.out.println("�������� ��  "+totalSize);
		//������������ĵ�
		System.out.println("���ص� ��  ");
		source.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		System.out.println("����� ��  ");
		source.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);

		System.out.println("���ص�Ů�� ��  ");
		source.stream().filter((person)->!person.isMale()).collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//��������
		int totalWeight=source.stream().collect(Collectors.summingInt(Person::getWeight));
		System.out.println("�������� ��  "+totalWeight+" KG");
		//ƽ������
		double avarageWeight=source.stream().collect(Collectors.averagingInt(Person::getWeight));
		System.out.println("ƽ������ ��  "+avarageWeight+" KG");
		//��������
		IntSummaryStatistics summary=source.stream().collect(Collectors.summarizingInt(Person::getWeight));
		System.out.println("�������ݣ�  "+summary);
		//�����б�
		String nameList= source.stream().map(Person::getName).distinct().collect(Collectors.joining(","));
		System.out.println("�����б�  "+nameList);
	}

}
