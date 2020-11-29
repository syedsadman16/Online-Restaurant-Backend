package com.cs322.ors.model;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Embeddable
public class TimeSlot {
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	 @Column(name = "TIME_FROM")
	private LocalDateTime from;
	
	 @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	 @JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "TIME_TO")
    private LocalDateTime to;

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	public TimeSlot() {
	}

	public TimeSlot(LocalDateTime from, LocalDateTime to) {
		super();
		this.from = from;
		this.to = to;
	}
    

}
