package com.revature.model;

public class Request {

	private int requestID;
	private Employee employee;
	private double amount;
	private String status;
	private Event event;
	private String justification;
	
	public Request() {
		super();
	}

	public Request(int requestID, Employee employee, double amount, String status, Event event, String justification) {
		super();
		this.requestID = requestID;
		this.employee = employee;
		this.amount = amount;
		this.status = status;
		this.event = event;
		this.justification = justification;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
}
