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
		//原始数据打印
		System.out.println("原始数据打印 : ");
		source.stream().forEach(System.out::println);
		//样本总数
		long totalSize= source.stream().collect(Collectors.counting());
		System.out.println("样本总数 ：  "+totalSize);
		//体重最重最轻的的
		System.out.println("最重的 ：  ");
		source.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		System.out.println("最轻的 ：  ");
		source.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);

		System.out.println("最重的女孩 ：  ");
		source.stream().filter((person)->!person.isMale()).collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//体重总量
		int totalWeight=source.stream().collect(Collectors.summingInt(Person::getWeight));
		System.out.println("体重总数 ：  "+totalWeight+" KG");
		//平均重量
		double avarageWeight=source.stream().collect(Collectors.averagingInt(Person::getWeight));
		System.out.println("平均体重 ：  "+avarageWeight+" KG");
		//汇总数据
		IntSummaryStatistics summary=source.stream().collect(Collectors.summarizingInt(Person::getWeight));
		System.out.println("汇总数据：  "+summary);
		//名字列表
		String nameList= source.stream().map(Person::getName).distinct().collect(Collectors.joining(","));
		System.out.println("名字列表：  "+nameList);
	}

}
