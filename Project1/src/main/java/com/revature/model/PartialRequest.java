package com.revature.model;

public class PartialRequest 
{
	private String event_type;
	
	private String grading_scale;
	
	private String start_date;
	
	private String end_date;
	
	private String event_location;
	
	private double amount;
	
	private String Description;
	
	private String Justification;

	public PartialRequest(String event_type, String grading_scale, String start_date, String end_date,
			String event_location, double amount, String description, String justification) 
	{
		super();
		this.event_type = event_type;
		this.grading_scale = grading_scale;
		this.start_date = start_date;
		this.end_date = end_date;
		this.event_location = event_location;
		this.amount = amount;
		Description = description;
		Justification = justification;
	}

	public PartialRequest() 
	{
		super();
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getGrading_scale() {
		return grading_scale;
	}

	public void setGrading_scale(String grading_scale) {
		this.grading_scale = grading_scale;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getEvent_location() {
		return event_location;
	}

	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getJustification() {
		return Justification;
	}

	public void setJustification(String justification) {
		Justification = justification;
	}
	
	
	
}
