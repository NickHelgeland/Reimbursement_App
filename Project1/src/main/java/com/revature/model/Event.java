package com.revature.model;

public class Event {
	private int eventId;
	private String eventType;
	private GradingFormat gradingFormat;
	private String eventDate;
	private String eventTime;
	private String eventLocation;
	private String eventDescription;
	private String eventStatus;
	
	public Event() {
		super();
	}

	public Event(int eventId, String eventType, GradingFormat gradingFormat, String eventDate, String eventTime,
			String eventLocation, String eventDescription, String eventStatus) {
		super();
		this.eventId = eventId;
		this.eventType = eventType;
		this.gradingFormat = gradingFormat;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventDescription = eventDescription;
		this.eventStatus = eventStatus;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public GradingFormat getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(GradingFormat gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
}
