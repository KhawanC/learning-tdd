package com.kaua.tests.dto;

public class StudentDTO {

	private Long id;

	private String name;

	private String courseName;

	public StudentDTO() {
	}

	public StudentDTO(Long id, String name, String courseName) {
		this.id = id;
		this.name = name;
		this.courseName = courseName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
