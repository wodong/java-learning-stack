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
		List<Person> source = Arrays.asList(new Person("Li Lei", 90,true,"��ѧ"), new Person("Li Lei", 98,true,"Сѧ"),
				new Person("Han Meimei", 60,false,"��ѧ"), new Person("Xiao wang", 82,false,"��ѧ"), new Person("Xiao zhang", 50,false,"Сѧ"),new Person("Xiao li", 94,false,"��ѧ"));
		//ԭʼ���ݴ�ӡ
		System.out.println("ԭʼ���ݴ�ӡ : ");
		source.stream().forEach(System.out::println);
		//Li Lei : 90 KG , ��, ��ѧ
		//Li Lei : 98 KG , ��, Сѧ
		//Han Meimei : 60 KG , Ů, ��ѧ
		//Xiao wang : 82 KG , Ů, ��ѧ
		//Xiao zhang : 50 KG , Ů, Сѧ
		//Xiao li : 94 KG , Ů, ��ѧ
		
		//��������
		long totalSize= source.stream().collect(Collectors.counting());
		System.out.println("�������� ��  "+totalSize);
		//�������� ��  6
		
		//������������ĵ�
		System.out.println("���ص� ��  ");
		source.stream().collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//Li Lei : 98 KG , ��, Сѧ
		
		System.out.println("����� ��  ");
		source.stream().collect(Collectors.minBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//Xiao zhang : 50 KG , Ů, Сѧ
		
		System.out.println("���ص�Ů�� ��  ");
		source.stream().filter((person)->!person.isMale()).collect(Collectors.maxBy(Comparator.comparingInt(Person::getWeight))).ifPresent(System.out::println);
		//Xiao li : 94 KG , Ů, ��ѧ
		
		//��������
		int totalWeight=source.stream().collect(Collectors.summingInt(Person::getWeight));
		System.out.println("�������� ��  "+totalWeight+" KG");
		//�������� ��  474 KG
		
		//����һ�ַ�������������
		int totalWeight2=source.stream().collect(Collectors.reducing(0,Person::getWeight,(i,j)->i+j));
		System.out.println("��������2 ��  "+totalWeight2+" KG");
		//��������2 ��  474 KG
		
		//ƽ������
		double avarageWeight=source.stream().collect(Collectors.averagingInt(Person::getWeight));
		System.out.println("ƽ������ ��  "+avarageWeight+" KG");
		//ƽ������ ��  79.0 KG
		
		//��������
		IntSummaryStatistics summary=source.stream().collect(Collectors.summarizingInt(Person::getWeight));
		System.out.println("�������ݣ�  "+summary);
		//�������ݣ�  IntSummaryStatistics{count=6, sum=474, min=50, average=79.000000, max=98}

		//�����б�
		String nameList= source.stream().map(Person::getName).distinct().collect(Collectors.joining(","));
		System.out.println("�����б�  "+nameList);
		//�����б�  Li Lei,Han Meimei,Xiao wang,Xiao zhang,Xiao li
		
		//��������������
		System.out.println("�������������飺  ");
		Map<String,List<Person>> personsByGender = source.stream().collect(Collectors.groupingBy(Person::getEducation));
		System.out.println(personsByGender);
		//{��ѧ=[Han Meimei : 60 KG , Ů, ��ѧ, Xiao wang : 82 KG , Ů, ��ѧ], ��ѧ=[Li Lei : 90 KG , ��, ��ѧ, Xiao li : 94 KG , Ů, ��ѧ], Сѧ=[Li Lei : 98 KG , ��, Сѧ, Xiao zhang : 50 KG , Ů, Сѧ]}

		//�������飬�Ƚ���������������
		System.out.println("�������飬�Ƚ��������������أ�  ");
		Map<String,Map<Boolean,List<Person>>> personsByGenderAndWeight = source.stream().collect(Collectors.groupingBy(Person::getEducation, Collectors.groupingBy(Person::isOverweight)));
		System.out.println(personsByGenderAndWeight);
		//{��ѧ={false=[Han Meimei : 60 KG , Ů, ��ѧ], true=[Xiao wang : 82 KG , Ů, ��ѧ]}, ��ѧ={true=[Li Lei : 90 KG , ��, ��ѧ, Xiao li : 94 KG , Ů, ��ѧ]}, Сѧ={false=[Xiao zhang : 50 KG , Ů, Сѧ], true=[Li Lei : 98 KG , ��, Сѧ]}}

		//ͳ�Ʒ�������
		System.out.println("�������������鲢ͳ������ �� ");
		Map<String,Long> personsByGenderCount = source.stream().collect(Collectors.groupingBy(Person::getEducation,Collectors.counting()));
		System.out.println(personsByGenderCount);
		//{��ѧ=2, ��ѧ=2, Сѧ=2}
		
		System.out.println("�������飬�Ƚ��������������أ���ͳ�������� ");
		Map<String,Map<Boolean,Long>> personsByGenderAndWeightCount = source.stream().collect(Collectors.groupingBy(Person::getEducation, Collectors.groupingBy(Person::isOverweight,Collectors.counting())));
		System.out.println(personsByGenderAndWeightCount);
		//{��ѧ={false=1, true=1}, ��ѧ={true=2}, Сѧ={false=1, true=1}}
		
		//��ͬ�������������ص�
		System.out.println("��ͬ�������������صģ�");
		Map<String,Optional<Person>> personsByGenderAndWeightMax= source.stream().collect(Collectors.groupingBy(Person::getEducation,Collectors.maxBy(Comparator.comparing(Person::getWeight))));
		System.out.println(personsByGenderAndWeightMax);
		//{��ѧ=Optional[Xiao wang : 82 KG , Ů, ��ѧ], ��ѧ=Optional[Xiao li : 94 KG , Ů, ��ѧ], Сѧ=Optional[Li Lei : 98 KG , ��, Сѧ]}

		//����
		System.out.println("�����Ա������");
		Map<Boolean,List<Person>> partitionPerson=source.stream().collect(Collectors.partitioningBy(Person::isMale));
		System.out.println(partitionPerson);
		//{false=[Han Meimei : 60 KG , Ů, ��ѧ, Xiao wang : 82 KG , Ů, ��ѧ, Xiao zhang : 50 KG , Ů, Сѧ, Xiao li : 94 KG , Ů, ��ѧ], true=[Li Lei : 90 KG , ��, ��ѧ, Li Lei : 98 KG , ��, Сѧ]}

}

}
