package com.example.prasad.kafka.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@JsonProperty(value = "eventType", required = true)
	private String eventType;

	@JsonProperty(value = "event1", required = false)
	private EventType1 event1;

	@JsonProperty(value = "event2", required = false)
	private EventType2 event2;

	@JsonProperty(value = "event3", required = false)
	private EventType3 event3;

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventType() {
		return this.eventType;
	}

	public EventType1 getEvent1() {
		return event1;
	}

	public void setEvent1(EventType1 event1) {
		this.event1 = event1;
	}

	public EventType2 getEvent2() {
		return event2;
	}

	public void setEvent2(EventType2 event2) {
		this.event2 = event2;
	}

	public EventType3 getEvent3() {
		return event3;
	}

	public void setEvent3(EventType3 event3) {
		this.event3 = event3;
	}

	@Override
	public String toString() {
		return "{EventType='" + eventType + ", Event1=" + event1 + ", Event2=" + event2 + ", Event3=" + event3 + "}";
	}

}
