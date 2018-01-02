package com.guyuesoft.structure.hash;

public class Employee {
	private String name;
	private double salary;
	private int seniority;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object rhs) {
		// TODO Auto-generated method stub
		return rhs instanceof Employee && name.equals(((Employee) rhs).name);
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return name.hashCode();
	}
}
