package com.guyuesoft.java8.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo {

	public static void main(String[] args) {
		List<Person> source = Arrays.asList(new Person("Li Lei", 90), new Person("Li Lei", 92),
				new Person("Han Meimei", 60), new Person("Xiao wang", 82), new Person("Xiao zhang", 50));
		//原始数据打印
		System.out.println("原始数据打印 : ");
		source.stream().forEach(System.out::println);
		//Li Lei : 90 KG 
		//Li Lei : 92 KG 
		//Han Meimei : 60 KG 
		//Xiao wang : 82 KG 
		//Xiao zhang : 50 KG 
		
		// 用谓词筛选 filter
		System.out.println("用谓词筛选 : ");
		source.stream().filter(Person::isOverweight).forEach(System.out::println);
		//Li Lei : 90 KG 
		//Li Lei : 92 KG 
		//Xiao wang : 82 KG 
		
		// 筛选各异的元素 distinct
		System.out.println("筛选各异的元素: ");
		source.stream().filter(Person::isOverweight).distinct().forEach(System.out::println);
		//Li Lei : 90 KG 
		//Xiao wang : 82 KG 
		
		// 截短流 limit
		System.out.println("截短流: ");
		source.stream().filter(Person::isOverweight).distinct().limit(1).forEach(System.out::println);
		//Li Lei : 90 KG 
		
		// 跳过元素 skip
		System.out.println("跳过元素: ");
		source.stream().filter(Person::isOverweight).distinct().skip(1).forEach(System.out::println);
		//Xiao wang : 82 KG 
		
		// 排序 sorted
		System.out.println("根据体重排序: ");
		source.stream().sorted(Comparator.comparingInt(Person::getWeight)).forEach(System.out::println);
		//Xiao zhang : 50 KG 
		//Han Meimei : 60 KG 
		//Xiao wang : 82 KG 
		//Li Lei : 90 KG 
		//Li Lei : 92 KG 
		
		System.out.println("根据体重倒序并保存到另外List中: ");
		List<Person> target = source.stream().sorted(Comparator.comparing(Person::getWeight).reversed()).collect(Collectors.toList());
		System.out.println(Arrays.toString(target.toArray()));
		//[Li Lei : 92 KG , Li Lei : 90 KG , Xiao wang : 82 KG , Han Meimei : 60 KG , Xiao zhang : 50 KG ]

		// 求总数count
		long count= source.stream().count();
		System.out.println("总数 : " +count);
		//总数 : 5

		// 查找至少匹配一个 anyMatch
		boolean result = source.stream().anyMatch(Person::isOverweight);
		System.out.println("查找至少匹配一个(>80)的结果 ： " + result);
		//查找至少匹配一个(>80)的结果 ： true
		
		// 查找全部匹配 allMatch
		boolean result2 = source.stream().allMatch(Person::isOverweight);
		System.out.println("查找全部匹配(>80)的结果 ： " + result2);
		//查找全部匹配(>80)的结果 ： false
		
		// 查找没有一个匹配 noneMatch
		boolean result3 = source.stream().noneMatch(Person::isOverweight);
		System.out.println("查找没有一个匹配(>80)的结果 ： " + result3);
		//查找没有一个匹配(>80)的结果 ： false
		
		// 查找元素 findAny findFirst (采用parallelStream 代替Stream，用来展示以下两个方法在并行下返回结果可能不同）
		System.out.println("查找任意满足的一个元素 ： " + result);
		source.parallelStream().filter(Person::isOverweight).findAny().ifPresent(System.out::println);
		System.out.println("查找第一个满足的元素 ： " + result);
		source.parallelStream().filter(Person::isOverweight).findFirst().ifPresent(System.out::println);
		//查找任意满足的一个元素 ： true
		//Xiao wang : 82 KG 
		//查找第一个满足的元素 ： true
		//Li Lei : 90 KG 
		
		// 映射 map
		System.out.println("映射: ");
		source.stream().filter(Person::isOverweight).distinct().map(Person::getName).forEach(System.out::println);
		//Li Lei
		//Xiao wang
		
		// 流的扁平化 flatmap
		System.out.println("输出名字包含的单词（不用flatmap): ");
		source.stream().filter(Person::isOverweight).distinct().map(person -> person.getName().split(" "))
				.forEach((String[] values) -> System.out.println(Arrays.toString(values)));
		// [Li, Lei]
		// [Xiao, wang]
		
		System.out.println("输出名字包含的单词（采用flatmap): ");
		source.stream().filter(Person::isOverweight).distinct().map(person -> person.getName().split(" "))
				.flatMap(Arrays::stream).forEach(System.out::println);
		//Li
		//Lei
		//Xiao
		//wang
		
		// 规约 reduce
		// 求和
		System.out.println("求和: ");
		source.parallelStream().mapToInt(Person::getWeight).reduce(Integer::sum).ifPresent(System.out::println);
		//374
		
		// 最大值最小值
		System.out.println("最大值: ");
		source.parallelStream().mapToInt(Person::getWeight).reduce(Integer::max).ifPresent(System.out::println);
		//92
		
		System.out.println("最小值: ");
		source.parallelStream().mapToInt(Person::getWeight).reduce(Integer::min).ifPresent(System.out::println);
		//50
		
		//IntStream
		System.out.println("展示IntStream: ");
		IntStream.rangeClosed(1, 4).forEach(System.out::println);
		//1
		//2
		//3
		//4
		
		//文件流
		System.out.println("从文件中读取流: ");
		//Stream<String> lines=Files.lines(Paths.get("d:/test.txt")
		try(Stream<String> lines=new BufferedReader(new InputStreamReader(Demo.class.getResourceAsStream("/test.txt"))).lines()){
			lines.forEach(System.out::println);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//This is the first Line;
		//This is the Second Line;
		
	}

}
