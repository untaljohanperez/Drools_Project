package com.tertulia;

public class Student {
	private String name;
	private int age;
	private boolean isValid;
	
	public Student(String name, int age){
		this.name = name;
		this.age = age;
		this.isValid = false;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
}
