package com.example.prasad.kafka.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventType3 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String message;

	public EventType3(@JsonProperty("name") String name, @JsonProperty("message") String message) {
		this.name = name;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "[[ Name: " + name + ", Message: " + message + " ]]";
	}

}
