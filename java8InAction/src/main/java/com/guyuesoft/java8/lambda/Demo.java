package com.guyuesoft.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo {

	public static void main(String[] args) {
		List<Person> source= Arrays.asList(new Person("Li Lei", 90),new Person("Han Meimei", 60),new Person("Xiao wang", 82),new Person("Xiao zhang", 50));
		//找到体重大于80KG的人
		//采用匿名类方式
		List<Person> target1= customFilter(source, new CustomPredicate<Person>() {

			@Override
			public boolean test(Person t) {
				if(t.getWeight()>80) {
					return true;
				}
				return false;
			}
		});
		//采用Lambda表达式
		List<Person> target2= customFilter(source, (person)->person.getWeight()>80 );
		
		//采用stream和Lambda
		List<Person> target3=source.stream().filter((person)->person.getWeight()>80).collect(Collectors.toList());
		
		System.out.println("匿名类结果 ： "+Arrays.toString(target1.toArray()));
		System.out.println("自定义接口的Lambda 结果 ： "+Arrays.toString(target2.toArray()));
		System.out.println("Stream版本的Lambda 结果 ： "+Arrays.toString(target2.toArray()));
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
