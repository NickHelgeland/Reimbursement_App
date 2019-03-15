package com.revature.model;

public class Message 
{
	private int messageId;
	
	private String message;
	
	private int targetEmployeeId;
	
	private int senderId;
	
	public Message()
	{
		super();
	}
	
	public Message(int messageId, String message, int target, int sender)
	{
		this.messageId = messageId;
		this.message = message;
		this.targetEmployeeId = target;
		this.senderId = sender;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public int getTargetEmployeeId() 
	{
		return targetEmployeeId;
	}

	public void setTargetEmployeeId(int targetEmployeeId) 
	{
		this.targetEmployeeId = targetEmployeeId;
	}

	public int getMessageId() 
	{
		return messageId;
	}

	public void setMessageId(int messageId) 
	{
		this.messageId = messageId;
	}

	public int getSender() 
	{
		return senderId;
	}

	public void setSender(int sender) 
	{
		this.senderId = sender;
	}
	
}
