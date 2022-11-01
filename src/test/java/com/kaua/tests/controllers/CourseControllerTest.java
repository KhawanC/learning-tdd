package com.kaua.tests.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kaua.tests.dto.CourseDTO;
import com.kaua.tests.dto.StudentDTO;
import com.kaua.tests.repositories.CourseRepository;
import com.kaua.tests.services.CourseService;

import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	CourseRepository courseRepository;

	@InjectMocks
	CourseController courseController;

	@Mock
	CourseService courseService;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWritter = objectMapper.writer();

	List<StudentDTO> students_1 = new ArrayList<>();
	List<StudentDTO> students_2 = new ArrayList<>();
	List<StudentDTO> students_3 = new ArrayList<>();

	CourseDTO RECORD_1 = new CourseDTO(1L, "Curso do Kauã", students_1);
	CourseDTO RECORD_2 = new CourseDTO(2L, "Curso do Kian", students_2);
	CourseDTO RECORD_3 = new CourseDTO(3L, "Curso do Kevin", students_3);

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
	}

	@Test
	public void getAllCourses() throws Exception {
		List<CourseDTO> courses = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

		Mockito.when(courseService.findAll()).thenReturn(courses);

		mockMvc.perform(MockMvcRequestBuilders.get("/course").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].name", is("Curso do Kevin")));
	}
}
