package com.example.prasad.kafka.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartitionMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	private int identifier;

	public PartitionMessage(@JsonProperty("message") final String message,
			@JsonProperty("identifier") final int identifier) {
		this.message = message;
		this.identifier = identifier;
	}

	public String getMessage() {
		return message;
	}

	public int getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return "PartitionMessage::toString() {message='" + message + "\'" + ", identifier=" + identifier + "}";
	}

}
