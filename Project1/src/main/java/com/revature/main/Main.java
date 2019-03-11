package com.revature.main;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.database.EmployeeDAO;
import com.revature.database.Login;
import com.revature.database.RequestDAO;
import com.revature.model.Employee;
import com.revature.model.Event;
import com.revature.model.GradingFormat;
import com.revature.model.LoginInfo;
import com.revature.model.Request;

public class Main 
{
	public static void main(String[] args)
	{
		RequestDAO dao = new RequestDAO();
		
		Employee employee = new Employee();
		employee.setEmployeeId(2);
		
		Event event = new Event();
		event.setEventId(21);
		
		Request request = new Request(1,employee,800,"pending superior approval",event,"because i want to");
		
		try 
		{
			dao.createNew(request);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
