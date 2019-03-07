package com.revature.main;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.database.EmployeeDAO;
import com.revature.model.Employee;

public class Main 
{
	public static void main(String[] args)
	{
		EmployeeDAO dao = new EmployeeDAO();
		
		Employee employee = new Employee();
		
		employee.setEmployeeId(1);
		
		try 
		{
			ArrayList<Employee> list = dao.selectAll();
			
			for(Employee e : list)
			{
				System.out.println(e.getFirstName() + e.getLastName());
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
