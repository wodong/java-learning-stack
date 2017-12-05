package com.guyuesoft.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {

	public static void main(String[] args) {
		List<Person> source= Arrays.asList(new Person("Li Lei", 90),new Person("Han Meimei", 60),new Person("Xiao wang", 82),new Person("Xiao zhang", 50));
		//�ҵ����ش���80KG����
		//���������෽ʽ
		List<Person> target1= customFilter(source, new CustomPredicate<Person>() {

			@Override
			public boolean test(Person t) {
				if(t.getWeight()>80) {
					return true;
				}
				return false;
			}
		});
		//����Lambda���ʽ
		List<Person> target2= customFilter(source, (person)->person.getWeight()>80 );
		
		//����stream��Lambda
		List<Person> target3=source.stream().filter((person)->person.getWeight()>80).collect(Collectors.toList());
		
		System.out.println("�������� �� "+Arrays.toString(target1.toArray()));
		System.out.println("�Զ���ӿڵ�Lambda ��� �� "+Arrays.toString(target2.toArray()));
		System.out.println("Stream�汾��Lambda ��� �� "+Arrays.toString(target2.toArray()));
	}
	
	static List<Person> customFilter(List<Person> source, CustomPredicate<Person> p){
		List<Person> target=  new ArrayList<>();
		for(Person person: source) {
			if(p.test(person)) {
				target.add(person);
			}
		}
		return target;
	}
}
