package com.revature.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.model.Employee;

public class EmployeeDAO implements Select<Employee>
{
	ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
	
	@Override
	public Employee selectOne(int id) throws SQLException
	{	
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery
				("SELECT * FROM EMPLOYEE WHERE EMPLOYEEID=" + "'" + id + "'");
		
		Employee employee = new Employee();
		
		while(resultSet.next())
		{
			employee.setEmployeeId(resultSet.getInt(1));
			employee.setFirstName(resultSet.getString(2));
			employee.setLastName(resultSet.getString(3));
			employee.setStreetAddress(resultSet.getString(4));
			employee.setCity(resultSet.getString(5));
			employee.setStateName(resultSet.getString(6));
			employee.setZipCode(resultSet.getInt(7));
			employee.setUsername(resultSet.getString(8));
			employee.setSupervisorId(resultSet.getInt(9));
			employee.setEmployeeType(resultSet.getString(10));
		}
		
		return employee;
	}

	@Override
	public ArrayList<Employee> selectAll() throws SQLException
	{
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE");
		
		ArrayList<Employee> list = new ArrayList<Employee>();
		
		while(resultSet.next())
		{
			Employee employee = new Employee();
			
			employee.setEmployeeId(resultSet.getInt(1));
			employee.setFirstName(resultSet.getString(2));
			employee.setLastName(resultSet.getString(3));
			employee.setStreetAddress(resultSet.getString(4));
			employee.setCity(resultSet.getString(5));
			employee.setStateName(resultSet.getString(6));
			employee.setZipCode(resultSet.getInt(7));
			employee.setUsername(resultSet.getString(8));
			employee.setSupervisorId(resultSet.getInt(9));
			employee.setEmployeeType(resultSet.getString(10));
			
			list.add(employee);
		}
		
		return list;
	}
	
	public int selectEmployeeId(String username) throws SQLException
	{
		int id = 1;
		
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT EMPLOYEEID FROM EMPLOYEE WHERE USERNAME=" 
				+ "'" + username + "'");
		
		while(resultSet.next())
		{
			id = resultSet.getInt(1);
		}
		
		return id;
	}
	
	public ArrayList<Employee> getAllSubordinates(int id, ArrayList<Employee> list) throws SQLException
	{	
		Connection connection = connectionFactory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEE WHERE SUPERVISORID=" 
				+ "'" + id + "'");
		
		while(resultSet.next())
		{
			Employee employee = new Employee();
			
			employee.setEmployeeId(resultSet.getInt(1));
			employee.setFirstName(resultSet.getString(2));
			employee.setLastName(resultSet.getString(3));
			employee.setStreetAddress(resultSet.getString(4));
			employee.setCity(resultSet.getString(5));
			employee.setStateName(resultSet.getString(6));
			employee.setZipCode(resultSet.getInt(7));
			employee.setUsername(resultSet.getString(8));
			employee.setSupervisorId(resultSet.getInt(9));
			employee.setEmployeeType(resultSet.getString(10));
			
			list.add(employee);
			
			if(employee.getEmployeeId() != 0)
			{
				this.getAllSubordinates(employee.getEmployeeId(), list);
			}
		}
		
		return list;
	}
}
