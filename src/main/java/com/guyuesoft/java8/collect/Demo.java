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
		//����һ�ַ�������������
		int totalWeight2=source.stream().collect(Collectors.reducing(0,Person::getWeight,(i,j)->i+j));
		System.out.println("��������2 ��  "+totalWeight2+" KG");
		//ƽ������
		double avarageWeight=source.stream().collect(Collectors.averagingInt(Person::getWeight));
		System.out.println("ƽ������ ��  "+avarageWeight+" KG");
		//��������
		IntSummaryStatistics summary=source.stream().collect(Collectors.summarizingInt(Person::getWeight));
		System.out.println("�������ݣ�  "+summary);
		//�����б�
		String nameList= source.stream().map(Person::getName).distinct().collect(Collectors.joining(","));
		System.out.println("�����б�  "+nameList);
		//��������������
		Map<String,List<Person>> personsByGender = source.stream().collect(Collectors.groupingBy(Person::getEducation));
		System.out.println(personsByGender);
		//�������飬�Ƚ���������������
		Map<String,Map<Boolean,List<Person>>> personsByGenderAndWeight = source.stream().collect(Collectors.groupingBy(Person::getEducation, Collectors.groupingBy(Person::isOverweight)));
		System.out.println(personsByGenderAndWeight);
		//ͳ�Ʒ�������
		Map<String,Long> personsByGenderCount = source.stream().collect(Collectors.groupingBy(Person::getEducation,Collectors.counting()));
		System.out.println(personsByGenderCount);
		Map<String,Map<Boolean,Long>> personsByGenderAndWeightCount = source.stream().collect(Collectors.groupingBy(Person::getEducation, Collectors.groupingBy(Person::isOverweight,Collectors.counting())));
		System.out.println(personsByGenderAndWeightCount);
		//��ͬ�������������ص�
		Map<String,Optional<Person>> personsByGenderAndWeightMax= source.stream().collect(Collectors.groupingBy(Person::getEducation,Collectors.maxBy(Comparator.comparing(Person::getWeight))));
		System.out.println(personsByGenderAndWeightMax);
		//����
		Map<Boolean,List<Person>> partitionPerson=source.stream().collect(Collectors.partitioningBy(Person::isMale));
		System.out.println(partitionPerson);
}

}
