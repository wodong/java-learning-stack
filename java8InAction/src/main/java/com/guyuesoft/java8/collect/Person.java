package com.guyuesoft.java8.collect;

//POJO, Person¶ÔÏó
public class Person {

	private String name;
	private int weight;
	private boolean isMale;
	private String education;
	
	
	public Person(String name, int weight, boolean isMale,String education) {
		super();
		this.name = name;
		this.weight = weight;
		this.isMale=isMale;
		this.education=education;
	}
	
	
	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
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
		return name+" : "+ weight +" KG "+(isMale?", ÄÐ":", Å®")+ ", "+education;
	}
	
	public boolean isOverweight() {
		return weight>80;
	}
	
	
	public boolean isMale() {
		return isMale;
	}
	public void setMale(boolean isMale) {
		this.isMale = isMale;
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
