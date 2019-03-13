package com.revature.model;

public class ApproveOrDeny 
{
	private boolean approved;
	
	private int id;
	
	public ApproveOrDeny()
	{
		super();
	}
	
	public ApproveOrDeny(boolean approved, int id)
	{
		this.approved = approved;
		this.id = id;
	}

	public boolean isApproved() 
	{
		return approved;
	}

	public void setApproved(boolean approved) 
	{
		this.approved = approved;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	
}
