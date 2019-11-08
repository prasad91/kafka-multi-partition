package com.example.prasad.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController2 {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private static int index = 0;

	@Value("${spring.kafka.topic-name3}")
	private String topicName;

	@GetMapping("/send-event-parent")
	public String sendEventParent(@RequestParam(value = "type") int eventType) throws Exception {
		switch (eventType) {
		case 1:
			com.example.prasad.kafka.domain2.Event event1 = new com.example.prasad.kafka.domain2.EventType1("Payment", "Payment-Attributes");
			event1.setEventType("Payment");
			this.template.send(topicName, String.valueOf(index++), event1);
			break;
		case 2:
			com.example.prasad.kafka.domain2.Event event2 = new com.example.prasad.kafka.domain2.EventType2("Account", "Account-Attributes");
			event2.setEventType("Account");
			this.template.send(topicName, String.valueOf(index++), event2);
			break;
		case 3:
			com.example.prasad.kafka.domain2.Event event3 = new com.example.prasad.kafka.domain2.EventType3("User", "User-Attributes");
			event3.setEventType("User");
			this.template.send(topicName, String.valueOf(index++), event3);
			break;
		default:
			return "No events generated!!\n";
		}

		return "Messages Sent!!\n";
	}
	
	@GetMapping("/send-event-standalone")
	public String sendEventStandalone(@RequestParam(value = "type") int eventType) throws Exception {
		switch (eventType) {
		case 1:
			com.example.prasad.kafka.domain3.EventType1 event1 = new com.example.prasad.kafka.domain3.EventType1("Payment", "Payment-Attributes");
			this.template.send(topicName, String.valueOf(index++), event1);
			break;
		case 2:
			com.example.prasad.kafka.domain3.EventType2 event2 = new com.example.prasad.kafka.domain3.EventType2("Account", "Account-Attributes");
			this.template.send(topicName, String.valueOf(index++), event2);
			break;
		case 3:
			com.example.prasad.kafka.domain3.EventType3 event3 = new com.example.prasad.kafka.domain3.EventType3("User", "User-Attributes");
			this.template.send(topicName, String.valueOf(index++), event3);
			break;
		default:
			return "No events generated!!\n";
		}

		return "Messages Sent!!\n";
	}

	@KafkaListener(topics = { "${spring.kafka.topic-name3}" }, containerFactory = "kafkaListenerContainerFactory")
	public void listenTopic1(ConsumerRecord<String, Object> cr, @Payload Object payload) {
		System.out.println("1 [JSON] received Key {" + cr.key() + "}: " + payload);
	}

}
