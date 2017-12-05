package com.guyuesoft.java8.lambda;

//POJO, Person∂‘œÛ
public class Person {

	private String name;
	private int weight;
	
	
	public Person(String name, int weight) {
		super();
		this.name = name;
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" : "+ weight +" KG ";
	}
	
}
