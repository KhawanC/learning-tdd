package com.kaua.tests.stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaua.tests.forms.CourseForm;
import com.kaua.tests.services.CourseService;

@Component
public class CourseConsumer {

	@Autowired
	CourseService service;

	private static final Logger log = LoggerFactory.getLogger(CourseConsumer.class);

	@KafkaListener(topics = { "course.form" }, groupId = "tdd-project", containerFactory = "kafkaListenerContainerFactory")
	public void formConsumer(ConsumerRecord<String, CourseForm> message) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			CourseForm form = mapper.convertValue(message.value(), CourseForm.class);
			service.save(form);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
