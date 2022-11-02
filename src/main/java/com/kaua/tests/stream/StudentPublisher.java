package com.kaua.tests.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kaua.tests.forms.StudentForm;

@Component
public class StudentPublisher {

	@Autowired
	KafkaTemplate<String, StudentForm> kafkaTemplate;

	private static final Logger log = LoggerFactory.getLogger(CoursePublisher.class);

	public void saveStudent(StudentForm form) throws Exception {
		try {
			kafkaTemplate.send("student.form", form);
			log.info("StudentForm was send");
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
