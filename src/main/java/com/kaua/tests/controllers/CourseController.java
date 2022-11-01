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

import com.kaua.tests.dto.CourseDTO;
import com.kaua.tests.forms.CourseForm;
import com.kaua.tests.services.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService service;

	@GetMapping
	public ResponseEntity<List<CourseDTO>> findAllCourses() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<CourseDTO> findCoursesById(@RequestParam Long id) {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<CourseDTO> findCoursesByName(@RequestParam String name) {
		return new ResponseEntity<>(service.findByName(name), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<CourseDTO> saveCourse(@RequestBody CourseForm form) {
		return new ResponseEntity<>(service.save(form), HttpStatus.OK);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<CourseDTO> deleteCourse(@RequestParam Long id) {
		return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
	}

}
