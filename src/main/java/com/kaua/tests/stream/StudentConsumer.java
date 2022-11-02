package com.kaua.tests.stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaua.tests.forms.StudentForm;
import com.kaua.tests.services.StudentService;

@Component
public class StudentConsumer {

	@Autowired
	StudentService service;

	private static final Logger log = LoggerFactory.getLogger(StudentConsumer.class);

	@KafkaListener(topics = { "student.form" }, groupId = "tdd-project", containerFactory = "kafkaListenerContainerFactory")
	public void formConsumer(ConsumerRecord<String, StudentForm> message) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			StudentForm form = mapper.convertValue(message.value(), StudentForm.class);
			service.save(form);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}
}
