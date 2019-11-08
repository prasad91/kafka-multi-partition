package com.example.prasad.kafka.domain2;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@JsonProperty(value = "eventType", required = true)
	private String eventType;

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return this.eventType;
	}

}
