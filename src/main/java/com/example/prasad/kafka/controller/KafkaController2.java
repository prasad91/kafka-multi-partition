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

import com.example.prasad.kafka.domain.Event;
import com.example.prasad.kafka.domain.EventType1;
import com.example.prasad.kafka.domain.EventType2;
import com.example.prasad.kafka.domain.EventType3;

@RestController
public class KafkaController2 {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private static int index = 0;

	@Value("${spring.kafka.new-topic-name}")
	private String topicName;

	@Value("${spring.kafka.new-topic-name2}")
	private String topicName2;

	@Value("${spring.kafka.new-topic-name3}")
	private String topicName3;

	@GetMapping("/send-event")
	public String topic1(@RequestParam(value = "type") int eventType) throws Exception {
		switch (eventType) {
		case 1:
			Event event1 = new Event();
			event1.setEventType("Payment");
			event1.setEvent1(new EventType1("Payment", "Payment-Attributes"));
			this.template.send(topicName, String.valueOf(index++), event1);
			break;
		case 2:
			Event event2 = new Event();
			event2.setEventType("Account");
			event2.setEvent2(new EventType2("Account", "Account-Attributes"));
			this.template.send(topicName2, String.valueOf(index++), event2);
			break;
		case 3:
			Event event3 = new Event();
			event3.setEventType("User");
			event3.setEvent3(new EventType3("User", "User-Attributes"));
			this.template.send(topicName3, String.valueOf(index++), event3);
			break;
		default:
			return "No events generated!!\n";
		}

		return "Messages Sent!!\n";
	}

	@KafkaListener(topics = { "${spring.kafka.new-topic-name}", "${spring.kafka.new-topic-name2}",
			"${spring.kafka.new-topic-name3}" }, containerFactory = "kafkaListenerContainerFactory")
	public void listenTopic1(ConsumerRecord<String, Event> cr, @Payload Event payload) {
		System.out.println("1 [JSON] received Key {" + cr.key() + "}: " + payload);
	}

}
