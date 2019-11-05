package com.example.prasad.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
	@Value("${spring.kafka.topic-name}")
	private String topicName;

	@Bean
	public NewTopic newTopic() {
		return new NewTopic(topicName, 3, (short) 1);
	}

	@Value("${spring.kafka.topic-name2}")
	private String topicName2;

	@Bean
	public NewTopic newTopic2() {
		return new NewTopic(topicName2, 3, (short) 1);
	}

	@Value("${spring.kafka.new-topic-name}")
	private String newTopicName;

	@Bean
	public NewTopic newTopic3() {
		return new NewTopic(newTopicName, 1, (short) 1);
	}

	@Value("${spring.kafka.new-topic-name2}")
	private String newTopicName2;

	@Bean
	public NewTopic newTopic4() {
		return new NewTopic(newTopicName2, 1, (short) 1);
	}

	@Value("${spring.kafka.new-topic-name3}")
	private String newTopicName3;

	@Bean
	public NewTopic newTopic5() {
		return new NewTopic(newTopicName3, 1, (short) 1);
	}
}