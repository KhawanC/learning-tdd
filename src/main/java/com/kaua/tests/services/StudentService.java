package com.kaua.tests.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaua.tests.converters.StudentConverter;
import com.kaua.tests.dto.StudentDTO;
import com.kaua.tests.forms.StudentForm;
import com.kaua.tests.models.Student;
import com.kaua.tests.repositories.StudentRepository;
import com.kaua.tests.stream.StudentPublisher;

@Service
public class StudentService {

	@Autowired
	StudentConverter converter;

	@Autowired
	StudentRepository repository;

	@Autowired
	StudentPublisher publisher;

	public List<StudentDTO> findAll() {
		StudentDTO dto = new StudentDTO();
		List<StudentDTO> listDto = new ArrayList<>();

		repository.findAll().forEach(student -> listDto.add(converter.ModelToDTO(student, dto)));
		return listDto;
	}

	public StudentDTO findById(Long id) throws NoSuchElementException {
		StudentDTO dto = new StudentDTO();

		Student student = repository.findById(id).orElseThrow(
				() -> new NoSuchElementException("It wasn't possible to found the student with the id " + id));
		return converter.ModelToDTO(student, dto);
	}

	public StudentDTO findByName(String name) throws NoSuchElementException {
		StudentDTO dto = new StudentDTO();

		Student student = repository.findByName(name).orElseThrow(
				() -> new NoSuchElementException("It wasn't possible to found the student with the name " + name));

		return converter.ModelToDTO(student, dto);
	}

	public StudentDTO save(StudentForm form) {
		StudentDTO dto = new StudentDTO();

		Student student = converter.FormToModel(form);
		Student newCourse = repository.save(student);

		return converter.ModelToDTO(newCourse, dto);
	}

	public StudentForm saveByKafka(StudentForm form) throws Exception {
		publisher.saveStudent(form);

		return form;
	}

	public StudentDTO delete(Long id) {
		StudentDTO student = this.findById(id);
		repository.deleteById(id);

		return student;
	}
}
