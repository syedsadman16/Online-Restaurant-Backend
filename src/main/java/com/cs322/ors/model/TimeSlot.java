package com.cs322.ors.model;

import java.time.LocalDateTime;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TimeSlot {
	 @Column(name = "TIME_FROM")
	private LocalDateTime from;
	
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
