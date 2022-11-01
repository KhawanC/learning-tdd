package com.kaua.tests.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kaua.tests.dto.StudentDTO;
import com.kaua.tests.forms.StudentForm;
import com.kaua.tests.services.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService service;
	
	@GetMapping
	public ResponseEntity<List<StudentDTO>> findAllStudent() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<StudentDTO> findStudentById(@RequestParam Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<StudentDTO> findStudentByName(@RequestParam String name) {
		return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentForm form) {
		return new ResponseEntity<>(service.save(form), HttpStatus.OK);
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<StudentDTO> deleteCourse(@RequestParam Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}
	
}
