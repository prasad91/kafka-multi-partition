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
public class KafkaController3 {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private static int index = 0;

	@Value("${spring.kafka.topic-name4}")
	private String topicName2;

	@GetMapping("/send-event-multi-group")
	public String sendEventMultiGroup(@RequestParam(value = "type") int eventType) throws Exception {
		switch (eventType) {
		case 1:
			com.example.prasad.kafka.domain2.Event event1 = new com.example.prasad.kafka.domain2.EventType1("Payment", "Payment-Attributes");
			event1.setEventType("Payment");
			this.template.send(topicName2, String.valueOf(index++), event1);
			break;
		case 2:
			com.example.prasad.kafka.domain2.Event event2 = new com.example.prasad.kafka.domain2.EventType2("Account", "Account-Attributes");
			event2.setEventType("Account");
			this.template.send(topicName2, String.valueOf(index++), event2);
			break;
		case 3:
			com.example.prasad.kafka.domain2.Event event3 = new com.example.prasad.kafka.domain2.EventType3("User", "User-Attributes");
			event3.setEventType("User");
			this.template.send(topicName2, String.valueOf(index++), event3);
			break;
		default:
			return "No events generated!!\n";
		}

		return "Messages Sent!!\n";
	}

	@KafkaListener(topics = { "${spring.kafka.topic-name4}" }, groupId = "${spring.kafka.consumer.group-id2}", containerFactory = "kafkaListenerContainerFactory")
	public void listenTopicGroup1(ConsumerRecord<String, Object> cr, @Payload Object payload) {
		System.out.println("[GROUP1] received Key {" + cr.key() + "}: " + payload);
	}
	
	@KafkaListener(topics = { "${spring.kafka.topic-name4}" }, groupId = "${spring.kafka.consumer.group-id3}", containerFactory = "kafkaListenerContainerFactory2")
	public void listenTopicGroup2(ConsumerRecord<String, Object> cr, @Payload Object payload) {
		System.out.println("[GROUP2] received Key {" + cr.key() + "}: " + payload);
	}

}
