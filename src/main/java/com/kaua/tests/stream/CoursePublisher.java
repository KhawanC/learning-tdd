package com.kaua.tests.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kaua.tests.forms.CourseForm;

@Component
public class CoursePublisher {

	@Autowired
	KafkaTemplate<String, CourseForm> kafkaTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(CoursePublisher.class);
	
	public void saveCourse(CourseForm form) throws Exception{
		try {
			kafkaTemplate.send("course.form", form);
			log.info("CourseForm was send");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		
	}
}
