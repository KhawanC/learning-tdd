package com.kaua.tests.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kaua.tests.models.Student;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {

	@Autowired
	StudentRepository repository;

	private static final Logger log = LoggerFactory.getLogger(StudentRepositoryTest.class);

	@Test
	public void shouldSaveStudent() {
		log.info("Starting 'shouldSaveStudent'");

		Student student = new Student(123L, "Kian", null);

		log.info("Saving student");
		Student newStudent = repository.save(student);

		assertThat(newStudent).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

		log.info("Test completed successfully");
	}

	@Test
	public void shouldGetStudent() {
		log.info("Starting 'shouldGetStudent'");

		Student student = new Student(123L, "Kaua", null);
		repository.save(student);

		log.info("Getting student");
		Student newStudent = repository.findByName(student.getName()).get();

		assertThat(newStudent).usingRecursiveComparison().ignoringFields("id").isEqualTo(student);

		log.info("Test completed successfully");
	}
	
	@Test
	public void shouldDeleteStudent() throws NoSuchElementException{
		log.info("Starting 'shouldDeleteStudent'");
		
		Student student = new Student(123L, "Kelvin", null);
		Student newStudent = repository.save(student);

		log.info("Deleting student");
		repository.deleteById(newStudent.getId());
		
		assertThrows(NoSuchElementException.class,
				() -> repository.findById(newStudent.getId()).get());
	}
}
