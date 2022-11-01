package com.kaua.tests.converters;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaua.tests.dto.StudentDTO;
import com.kaua.tests.forms.StudentForm;
import com.kaua.tests.models.Student;
import com.kaua.tests.repositories.CourseRepository;

@Component
public class StudentConverter {

	@Autowired
	CourseRepository repository;

	public Student FormToModel(StudentForm form) throws NoSuchElementException {
		Student model = new Student();

		model.setName(form.getName());
		if (form.getCourseName() != null) {
			model.setCourse(repository.findByName(form.getCourseName()).orElseThrow(() -> new NoSuchElementException(
					"It wasn't possible to found the course with the name " + form.getCourseName())));
		} else {
			model.setCourse(null);
		}

		return model;
	}

	public StudentDTO ModelToDTO(Student model, StudentDTO dto) {
		if(model.getCourse() != null) {
			dto.setCourseName(model.getCourse().getName());
		} else {
			dto.setCourseName(null);
		}
		
		dto.setId(model.getId());
		dto.setName(model.getName());

		return dto;
	}
}
