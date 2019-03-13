package com.revature.main;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.database.EmployeeDAO;
import com.revature.database.EventDAO;
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
		
		EmployeeDAO edao = new EmployeeDAO();
		
		Employee employee = new Employee();
		employee.setEmployeeId(2);
		
		Event event = new Event();
		event.setEventId(21);
		
		EventDAO eventDAO = new EventDAO();
		
		RequestDAO requestDAO = new RequestDAO();
		
		//Request request = new Request(1,employee,800,"pending superior approval",event,"because i want to");
		
		try 
		{
			Event newEvent = eventDAO.selectOne(requestDAO.selectOne(41).getEvent().getEventId());
			
			System.out.println(newEvent.getEventLocation() + " " + newEvent.getEventDescription());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
