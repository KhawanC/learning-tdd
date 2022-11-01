package com.kaua.tests.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaua.tests.converters.CourseConverter;
import com.kaua.tests.dto.CourseDTO;
import com.kaua.tests.forms.CourseForm;
import com.kaua.tests.models.Course;
import com.kaua.tests.repositories.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseConverter converter;

	@Autowired
	CourseRepository repository;

	public List<CourseDTO> findAll() {
		CourseDTO dto = new CourseDTO();
		List<CourseDTO> listDto = new ArrayList<>();

		repository.findAll().forEach(course -> listDto.add(converter.ModelToDTO(course, dto)));
		return listDto;
	}

	public CourseDTO findById(Long id) throws EntityNotFoundException {
		CourseDTO dto = new CourseDTO();

		Course course = repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException("It wasn't possible to found the course with the id " + id));

		return converter.ModelToDTO(course, dto);
	}

	public CourseDTO findByName(String name) {
		CourseDTO dto = new CourseDTO();

		Course course = repository.findByName(name).orElseThrow(
				() -> new EntityNotFoundException("It wasn't possible to found the course with the name " + name));
		return converter.ModelToDTO(course, dto);
	}

	public CourseDTO save(CourseForm form) {
		CourseDTO dto = new CourseDTO();

		Course course = converter.FormToModel(form);
		Course newCourse = repository.save(course);

		return converter.ModelToDTO(newCourse, dto);
	}

	public CourseDTO delete(Long id) {
		CourseDTO course = this.findById(id);
		repository.deleteById(id);

		return course;
	}
}
