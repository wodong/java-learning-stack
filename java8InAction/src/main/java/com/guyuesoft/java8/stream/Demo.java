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
		//ԭʼ���ݴ�ӡ
		System.out.println("ԭʼ���ݴ�ӡ : ");
		source.stream().forEach(System.out::println);
		//Li Lei : 90 KG 
		//Li Lei : 92 KG 
		//Han Meimei : 60 KG 
		//Xiao wang : 82 KG 
		//Xiao zhang : 50 KG 
		
		// ��ν��ɸѡ filter
		System.out.println("��ν��ɸѡ : ");
		source.stream().filter(Person::isOverweight).forEach(System.out::println);
		//Li Lei : 90 KG 
		//Li Lei : 92 KG 
		//Xiao wang : 82 KG 
		
		// ɸѡ�����Ԫ�� distinct
		System.out.println("ɸѡ�����Ԫ��: ");
		source.stream().filter(Person::isOverweight).distinct().forEach(System.out::println);
		//Li Lei : 90 KG 
		//Xiao wang : 82 KG 
		
		// �ض��� limit
		System.out.println("�ض���: ");
		source.stream().filter(Person::isOverweight).distinct().limit(1).forEach(System.out::println);
		//Li Lei : 90 KG 
		
		// ����Ԫ�� skip
		System.out.println("����Ԫ��: ");
		source.stream().filter(Person::isOverweight).distinct().skip(1).forEach(System.out::println);
		//Xiao wang : 82 KG 
		
		// ���� sorted
		System.out.println("������������: ");
		source.stream().sorted(Comparator.comparingInt(Person::getWeight)).forEach(System.out::println);
		//Xiao zhang : 50 KG 
		//Han Meimei : 60 KG 
		//Xiao wang : 82 KG 
		//Li Lei : 90 KG 
		//Li Lei : 92 KG 
		
		System.out.println("�������ص��򲢱��浽����List��: ");
		List<Person> target = source.stream().sorted(Comparator.comparing(Person::getWeight).reversed()).collect(Collectors.toList());
		System.out.println(Arrays.toString(target.toArray()));
		//[Li Lei : 92 KG , Li Lei : 90 KG , Xiao wang : 82 KG , Han Meimei : 60 KG , Xiao zhang : 50 KG ]

		// ������count
		long count= source.stream().count();
		System.out.println("���� : " +count);
		//���� : 5

		// ��������ƥ��һ�� anyMatch
		boolean result = source.stream().anyMatch(Person::isOverweight);
		System.out.println("��������ƥ��һ��(>80)�Ľ�� �� " + result);
		//��������ƥ��һ��(>80)�Ľ�� �� true
		
		// ����ȫ��ƥ�� allMatch
		boolean result2 = source.stream().allMatch(Person::isOverweight);
		System.out.println("����ȫ��ƥ��(>80)�Ľ�� �� " + result2);
		//����ȫ��ƥ��(>80)�Ľ�� �� false
		
		// ����û��һ��ƥ�� noneMatch
		boolean result3 = source.stream().noneMatch(Person::isOverweight);
		System.out.println("����û��һ��ƥ��(>80)�Ľ�� �� " + result3);
		//����û��һ��ƥ��(>80)�Ľ�� �� false
		
		// ����Ԫ�� findAny findFirst (����parallelStream ����Stream������չʾ�������������ڲ����·��ؽ�����ܲ�ͬ��
		System.out.println("�������������һ��Ԫ�� �� " + result);
		source.parallelStream().filter(Person::isOverweight).findAny().ifPresent(System.out::println);
		System.out.println("���ҵ�һ�������Ԫ�� �� " + result);
		source.parallelStream().filter(Person::isOverweight).findFirst().ifPresent(System.out::println);
		//�������������һ��Ԫ�� �� true
		//Xiao wang : 82 KG 
		//���ҵ�һ�������Ԫ�� �� true
		//Li Lei : 90 KG 
		
		// ӳ�� map
		System.out.println("ӳ��: ");
		source.stream().filter(Person::isOverweight).distinct().map(Person::getName).forEach(System.out::println);
		//Li Lei
		//Xiao wang
		
		// ���ı�ƽ�� flatmap
		System.out.println("������ְ����ĵ��ʣ�����flatmap): ");
		source.stream().filter(Person::isOverweight).distinct().map(person -> person.getName().split(" "))
				.forEach((String[] values) -> System.out.println(Arrays.toString(values)));
		// [Li, Lei]
		// [Xiao, wang]
		
		System.out.println("������ְ����ĵ��ʣ�����flatmap): ");
		source.stream().filter(Person::isOverweight).distinct().map(person -> person.getName().split(" "))
				.flatMap(Arrays::stream).forEach(System.out::println);
		//Li
		//Lei
		//Xiao
		//wang
		
		// ��Լ reduce
		// ���
		System.out.println("���: ");
		source.parallelStream().mapToInt(Person::getWeight).reduce(Integer::sum).ifPresent(System.out::println);
		//374
		
		// ���ֵ��Сֵ
		System.out.println("���ֵ: ");
		source.parallelStream().mapToInt(Person::getWeight).reduce(Integer::max).ifPresent(System.out::println);
		//92
		
		System.out.println("��Сֵ: ");
		source.parallelStream().mapToInt(Person::getWeight).reduce(Integer::min).ifPresent(System.out::println);
		//50
		
		//IntStream
		System.out.println("չʾIntStream: ");
		IntStream.rangeClosed(1, 4).forEach(System.out::println);
		//1
		//2
		//3
		//4
		
		//�ļ���
		System.out.println("���ļ��ж�ȡ��: ");
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
