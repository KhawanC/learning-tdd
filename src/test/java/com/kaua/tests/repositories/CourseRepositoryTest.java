package com.kaua.tests.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kaua.tests.models.Course;
import com.kaua.tests.models.Student;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTest {

	@Autowired
	CourseRepository repository;

	private static final Logger log = LoggerFactory.getLogger(CourseRepositoryTest.class);

	@Test
	public void shouldSaveCourse() {
		log.info("Starting 'shouldSaveCourse'");

		List<Student> students = new ArrayList<Student>();
		Course course = new Course(123L, "Curso do Kauã", students);

		log.info("Saving course");
		Course newCourse = repository.save(course);

		assertThat(newCourse).usingRecursiveComparison().ignoringFields("id").isEqualTo(course);

		log.info("Test completed successfully");
	}
	
	@Test
	public void shouldGetCourse() {
		log.info("Starting 'shouldGetStudent'");

		List<Student> students = new ArrayList<Student>();
		Course course = new Course(123L, "Curso do Kevin", students);
		repository.save(course);

		log.info("Getting student");
		Course newCourse = repository.findByName(course.getName()).get();

		assertThat(newCourse).usingRecursiveComparison().ignoringFields("id").isEqualTo(course);

		log.info("Test completed successfully");
	}
	
	@Test
	public void shouldDeleteCourse() throws NoSuchElementException{
		log.info("Starting 'shouldDeleteStudent'");
		
		List<Student> students = new ArrayList<Student>();
		Course course = new Course(123L, "Curso do Kian", students);
		Course newCourse = repository.save(course);

		log.info("Deleting student");
		repository.deleteById(newCourse.getId());
		
		assertThrows(NoSuchElementException.class,
				() -> repository.findById(newCourse.getId()).get());
	}
}
