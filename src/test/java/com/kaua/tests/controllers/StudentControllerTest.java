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
import com.kaua.tests.dto.StudentDTO;
import com.kaua.tests.repositories.StudentRepository;
import com.kaua.tests.services.StudentService;

import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentController studentController;

	@Mock
	StudentService studentService;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWritter = objectMapper.writer();

	StudentDTO RECORD_1 = new StudentDTO(1L, "Kauã", null);
	StudentDTO RECORD_2 = new StudentDTO(2L, "Kian", null);
	StudentDTO RECORD_3 = new StudentDTO(3L, "Kevin", null);

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
	}

	@Test
	public void getAllStudents() throws Exception {
		List<StudentDTO> students = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

		Mockito.when(studentService.findAll()).thenReturn(students);

		mockMvc.perform(MockMvcRequestBuilders.get("/student").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].name", is("Kevin")));
	}
}
