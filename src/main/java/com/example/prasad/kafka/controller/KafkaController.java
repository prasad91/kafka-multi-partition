package com.example.prasad.kafka.controller;

import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prasad.kafka.domain.PartitionMessage;

@RestController
public class KafkaController {

	private static final int MESSAGE_COUNT = 100;

	@Autowired
	private KafkaTemplate<String, Object> template;

	@Value("${spring.kafka.topic-name}")
	private String topicName;
	
	@Value("${spring.kafka.topic-name2}")
	private String topicName2;

	@GetMapping("/send-event")
	public String sendEvent() throws Exception {
		IntStream.range(0, MESSAGE_COUNT).forEach(index -> this.template.send(topicName, String.valueOf(index),
				new PartitionMessage("Prasad: ", 0)));
		IntStream.range(0, MESSAGE_COUNT).forEach(index -> this.template.send(topicName2, String.valueOf(index),
				new PartitionMessage("Prasad2: ", 0)));
		return "Messages Sent!!\n";
	}

	@KafkaListener(topics = {"${spring.kafka.topic-name}","${spring.kafka.topic-name2}"}, clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
	public void listenAsObject(ConsumerRecord<String, PartitionMessage> cr, @Payload PartitionMessage payload) {
		System.out.println("1 [JSON] received Key {" + cr.key() + "}: "
											+ "Type [{" + typeIdHeader(cr.headers()) + "}] | "
											+ "Payload: {" + payload + "} "
											+ "Record: {" + cr.toString() + "}");
	}

	@KafkaListener(topics = {"${spring.kafka.topic-name}","${spring.kafka.topic-name2}"}, clientIdPrefix = "string", containerFactory = "kafkaListenerStringContainerFactory")
	public void listenasString(ConsumerRecord<String, String> cr, @Payload String payload) {
		System.out.println("2 [String] received Key {" + cr.key() + "}: "
												+ "Type [{" + typeIdHeader(cr.headers())+ "}] | "
												+ "Payload: {" + payload + "} "
												+ "Record: {" + cr.toString() + "}");
	}

	@KafkaListener(topics = {"${spring.kafka.topic-name}","${spring.kafka.topic-name2}"}, clientIdPrefix = "bytearray", containerFactory = "kafkaListenerByteArrayContainerFactory")
	public void listenAsByteArray(ConsumerRecord<String, byte[]> cr, @Payload byte[] payload) {
		System.out.println("3 [ByteArray] received Key {" + cr.key() + "}: "
												+ "Type [{" + typeIdHeader(cr.headers())+ "}] | "
												+ "Payload: {" + payload + "} } "
												+ "Record: {" + cr.toString() + "}");
	}

	private static String typeIdHeader(Headers headers) {
		return StreamSupport.stream(headers.spliterator(), false).filter(header -> header.key().equals("__TypeId__"))
				.findFirst().map(header -> new String(header.value())).orElse("N/A");
	}
}
