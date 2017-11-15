package com.guyuesoft.java8.stream;

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
	
	public boolean isOverweight() {
		return weight>80;
	}
	
	@Override
	public boolean equals(Object target) {
		// TODO Auto-generated method stub
		return name.equals(((Person)target).getName());
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.hashCode();
	}
}
