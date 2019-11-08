package com.example.prasad.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController4 {

	@Autowired
	private KafkaTemplate<String, Object> template;

	private static int index = 0;

	@Value("${spring.kafka.topic-name5}")
	private String topicName;
	
	@Value("${spring.kafka.topic-name6}")
	private String topicName2;

	@GetMapping("/send-acknowledgment-event")
	public String sendEventAcknowledgment(@RequestParam int eventType, @RequestParam String event) throws Exception {
		switch (eventType) {
		case 1: //"break-auto-ack";
			com.example.prasad.kafka.domain2.Event event5 = new com.example.prasad.kafka.domain2.EventType1("Payment", "Payment-Attributes");
			event5.setEventType(event);
			this.template.send(topicName, String.valueOf(index++), event5);
			break;
		case 2: //"break-manual-ack";
			com.example.prasad.kafka.domain2.Event event6 = new com.example.prasad.kafka.domain2.EventType1("Payment", "Payment-Attributes");
			event6.setEventType(event);
			this.template.send(topicName2, String.valueOf(index++), event6);
			break;
		default:
			return "No events generated!!\n";
		}

		return "Messages Sent!!\n";
	}
	
	@KafkaListener(topics = { "${spring.kafka.topic-name5}" }, containerFactory = "kafkaListenerContainerFactory2")
	public void listenTopicAutoAck(ConsumerRecord<String, com.example.prasad.kafka.domain2.Event> cr, @Payload com.example.prasad.kafka.domain2.Event payload) {
		if("break-auto-ack".equalsIgnoreCase(payload.getEventType())) {
			//Thread.currentThread().stop();
		}
		System.out.println("[Auto ACK] received Key {" + cr.key() + "}: " + payload);
	}

	@KafkaListener(topics = { "${spring.kafka.topic-name6}" }, containerFactory = "kafkaListenerContainerFactory3")
	public void listenTopicManualAck(ConsumerRecord<String, com.example.prasad.kafka.domain2.Event> cr, @Payload com.example.prasad.kafka.domain2.Event payload, 
			Acknowledgment ack) {
		if("break-manual-ack-before".equalsIgnoreCase(payload.getEventType())) {
			//Thread.currentThread().stop();
		}
		System.out.println("[Manu ACK] received Key {" + cr.key() + "}: " + payload);
		ack.acknowledge();
		if("break-manual-ack-after".equalsIgnoreCase(payload.getEventType())) {
			//Thread.currentThread().stop();
		}
	}

}
