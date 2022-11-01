package com.kaua.tests.forms;

public class StudentForm {

	private String name;

	private String courseName;

	public StudentForm() {
	}

	public StudentForm(String name, String courseName) {
		super();
		this.name = name;
		this.courseName = courseName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
