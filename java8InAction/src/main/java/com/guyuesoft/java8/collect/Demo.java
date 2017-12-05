package com.guyuesoft.java8.collect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

	public static void main(String[] args) {
		List<Person> source = Arrays.asList(new Person("Li Lei", 90,true,"大学"), new Person("Li Lei", 98,true,"小学"),
				new Person("Han Meimei", 60,false,"中学"), new Person("Xiao wang", 82,false,"中学"), new Person("Xiao zhang", 50,false,"小学"),new Person("Xiao li", 94,false,"大学"));
		//原始数据打印
		System.out.println("原始数据打印 : ");
		source.stream().forEach(System.out::println);
		//Li Lei : 90 KG , 男, 大学
		//Li Lei : 98 KG , 男, 小学
		//Han Meimei : 60 KG , 女, 中学
		//Xiao wang : 82 KG , 女, 中学
		//Xiao zhang : 50 KG , 女, 小学
		//Xiao li : 94 KG , 女, 大学
		
		//样本总数
		long totalSize= source.stream().collect(Collectors.counting());
		System.out.println("样本总数 ：  "+totalSize);
		//样本总数 ：  6
		
		//体重最重最轻的的
		System.out.println("最重的 ：  ");
		source.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//Li Lei : 98 KG , 男, 小学
		
		System.out.println("最轻的 ：  ");
		source.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//Xiao zhang : 50 KG , 女, 小学
		
		System.out.println("最重的女孩 ：  ");
		source.stream().filter((person)->!person.isMale()).collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//Xiao li : 94 KG , 女, 大学
		
		//体重总量
		int totalWeight=source.stream().collect(Collectors.summingInt(Person::getWeight));
		System.out.println("体重总数 ：  "+totalWeight+" KG");
		//体重总数 ：  474 KG
		
		//另外一种方法求体重总量
		int totalWeight2=source.stream().collect(Collectors.reducing(0,Person::getWeight,(i,j)->i+j));
		System.out.println("体重总数2 ：  "+totalWeight2+" KG");
		//体重总数2 ：  474 KG
		
		//平均重量
		double avarageWeight=source.stream().collect(Collectors.averagingInt(Person::getWeight));
		System.out.println("平均体重 ：  "+avarageWeight+" KG");
		//平均体重 ：  79.0 KG
		
		//汇总数据
		IntSummaryStatistics summary=source.stream().collect(Collectors.summarizingInt(Person::getWeight));
		System.out.println("汇总数据：  "+summary);
		//汇总数据：  IntSummaryStatistics{count=6, sum=474, min=50, average=79.000000, max=98}

		//名字列表
		String nameList= source.stream().map(Person::getName).distinct().collect(Collectors.joining(","));
		System.out.println("名字列表：  "+nameList);
		//名字列表：  Li Lei,Han Meimei,Xiao wang,Xiao zhang,Xiao li
		
		//按教育背景分组
		System.out.println("按教育背景分组：  ");
		Map<String,List<Person>> personsByGender = source.stream().collect(Collectors.groupingBy(Person::getEducation));
		System.out.println(personsByGender);
		//{中学=[Han Meimei : 60 KG , 女, 中学, Xiao wang : 82 KG , 女, 中学], 大学=[Li Lei : 90 KG , 男, 大学, Xiao li : 94 KG , 女, 大学], 小学=[Li Lei : 98 KG , 男, 小学, Xiao zhang : 50 KG , 女, 小学]}

		//二级分组，先教育背景，再体重
		System.out.println("二级分组，先教育背景，再体重：  ");
		Map<String,Map<Boolean,List<Person>>> personsByGenderAndWeight = source.stream().collect(Collectors.groupingBy(Person::getEducation, Collectors.groupingBy(Person::isOverweight)));
		System.out.println(personsByGenderAndWeight);
		//{中学={false=[Han Meimei : 60 KG , 女, 中学], true=[Xiao wang : 82 KG , 女, 中学]}, 大学={true=[Li Lei : 90 KG , 男, 大学, Xiao li : 94 KG , 女, 大学]}, 小学={false=[Xiao zhang : 50 KG , 女, 小学], true=[Li Lei : 98 KG , 男, 小学]}}

		//统计分组人数
		System.out.println("按教育背景分组并统计人数 ： ");
		Map<String,Long> personsByGenderCount = source.stream().collect(Collectors.groupingBy(Person::getEducation,Collectors.counting()));
		System.out.println(personsByGenderCount);
		//{中学=2, 大学=2, 小学=2}
		
		System.out.println("二级分组，先教育背景，再体重，并统计人数： ");
		Map<String,Map<Boolean,Long>> personsByGenderAndWeightCount = source.stream().collect(Collectors.groupingBy(Person::getEducation, Collectors.groupingBy(Person::isOverweight,Collectors.counting())));
		System.out.println(personsByGenderAndWeightCount);
		//{中学={false=1, true=1}, 大学={true=2}, 小学={false=1, true=1}}
		
		//不同教育背景下最重的
		System.out.println("不同教育背景下最重的：");
		Map<String,Optional<Person>> personsByGenderAndWeightMax= source.stream().collect(Collectors.groupingBy(Person::getEducation,Collectors.maxBy(Comparator.comparing(Person::getWeight))));
		System.out.println(personsByGenderAndWeightMax);
		//{中学=Optional[Xiao wang : 82 KG , 女, 中学], 大学=Optional[Xiao li : 94 KG , 女, 大学], 小学=Optional[Li Lei : 98 KG , 男, 小学]}

		//分区
		System.out.println("根据性别分区：");
		Map<Boolean,List<Person>> partitionPerson=source.stream().collect(Collectors.partitioningBy(Person::isMale));
		System.out.println(partitionPerson);
		//{false=[Han Meimei : 60 KG , 女, 中学, Xiao wang : 82 KG , 女, 中学, Xiao zhang : 50 KG , 女, 小学, Xiao li : 94 KG , 女, 大学], true=[Li Lei : 90 KG , 男, 大学, Li Lei : 98 KG , 男, 小学]}

}

}
