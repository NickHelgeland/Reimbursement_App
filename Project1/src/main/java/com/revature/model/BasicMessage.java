package com.revature.model;

public class BasicMessage
{
	private String message;
	
	private int requestId;

	public BasicMessage()
	{
		
	}
	
	public BasicMessage(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public int getRequestId()
	{
		return requestId;
	}

	public void setRequestId(int requestId)
	{
		this.requestId = requestId;
	}
}
