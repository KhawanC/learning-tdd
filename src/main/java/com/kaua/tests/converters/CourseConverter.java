package com.kaua.tests.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaua.tests.dto.CourseDTO;
import com.kaua.tests.dto.StudentDTO;
import com.kaua.tests.forms.CourseForm;
import com.kaua.tests.models.Course;
import com.kaua.tests.models.Student;

@Component
public class CourseConverter {

	@Autowired
	StudentConverter studentConverter;

	public Course FormToModel(CourseForm form) {
		Course model = new Course();

		model.setName(form.getName());
		model.setStudents(new ArrayList<Student>());

		return model;
	}

	public CourseDTO ModelToDTO(Course model, CourseDTO dto) {
		List<StudentDTO> listStudentsDTO = new ArrayList<>();
		StudentDTO studentDTO = new StudentDTO();
		if(!model.getStudents().isEmpty())
			model.getStudents().forEach(student -> listStudentsDTO.add(studentConverter.ModelToDTO(student, studentDTO)));

		dto.setId(model.getId());
		dto.setName(model.getName());
		dto.setStudents(listStudentsDTO);

		return dto;
	}
}
