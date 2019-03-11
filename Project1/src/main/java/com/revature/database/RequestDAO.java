package com.revature.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.model.Request;

public class RequestDAO implements Insert<Request>, Update<Request>, Select<Request>
{
	private ConnectionFactory factory = ConnectionFactory.getInstance();
	
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	//EventDAO eventDAO = new EventDAO();
	
	@Override
	public void sendUpdate(Request object) throws SQLException
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery
				("SELECT * FROM REQUESTS WHERE REQUESTID=" + "'" + object.getRequestID() + "'");
		
		while(resultSet.next())
		{
			Statement update = connection.createStatement();
			
			if(object.getEmployee().getEmployeeId() != resultSet.getInt(2))
			{
				update.executeQuery("UPDATE REQUESTS SET EMPLOYEEID=" + "'" 
						+ object.getEmployee().getEmployeeId() + "' WHERE REQUESTID=" + "'" + 
						resultSet.getInt(1) + "'");
			}
			if(object.getAmount() != resultSet.getDouble(3))
			{
				update.executeQuery("UPDATE REQUESTS SET AMOUNT=" + "'" 
						+ object.getAmount() + "' WHERE REQUESTID=" + "'" + 
						resultSet.getInt(1) + "'");
			}
			if(!object.getStatus().equals(resultSet.getString(4)))
			{
				update.executeQuery("UPDATE REQUESTS SET STATUS=" + "'" 
						+ object.getStatus() + "' WHERE REQUESTID=" + "'" + 
						resultSet.getInt(1) + "'");
			}
			if(object.getEvent().getEventId() != resultSet.getInt(5))
			{
				update.executeQuery("UPDATE REQUESTS SET EVENTID=" + "'" 
						+ object.getEvent().getEventId() + "' WHERE REQUESTID=" + "'" + 
						resultSet.getInt(1) + "'");
			}
			if(!object.getJustification().equals(resultSet.getString(6)))
			{
				update.executeQuery("UPDATE REQUESTS SET JUSTIFICATION=" + "'" 
						+ object.getJustification() + "' WHERE REQUESTID=" + "'" + 
						resultSet.getInt(1) + "'");
			}
		}
	}

	@Override
	public void createNew(Request object) throws SQLException
	{
		Connection connection = factory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO REQUESTS "
				+ "VALUES(REQUESTID_SEQ.NEXTVAL,?,?,?,?,?)");
		
		statement.setInt(1, object.getEmployee().getEmployeeId());
		statement.setDouble(2, object.getAmount());
		statement.setString(3, object.getStatus());
		statement.setInt(4, object.getEvent().getEventId());
		statement.setString(5, object.getJustification());
		
		statement.executeQuery();
	}

	@Override
	public Request selectOne(int id) throws SQLException 
	{		
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery
				("SELECT * FROM REQUEST WHERE REQUESTID=" + "'" + id + "'");
		
		Request request = new Request();
		
		while(resultSet.next())
		{
			request.setRequestID(resultSet.getInt(1));
			request.setEmployee(employeeDAO.selectOne(resultSet.getInt(2)));
			request.setAmount(resultSet.getDouble(3));
			request.setStatus(resultSet.getString(4));
			//request.setEvent(eventDAO.selectOne(resultSet.getInt(5)));
			request.setJustification(resultSet.getString(6));
		}
		
		return request;
	}

	@Override
	public ArrayList<Request> selectAll() throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery
				("SELECT * FROM REQUEST");
		
		ArrayList<Request> list = new ArrayList<Request>();
		
		while(resultSet.next())
		{
			Request request = new Request();
			
			request.setRequestID(resultSet.getInt(1));
			request.setEmployee(employeeDAO.selectOne(resultSet.getInt(2)));
			request.setAmount(resultSet.getDouble(3));
			request.setStatus(resultSet.getString(4));
			//request.setEvent(eventDAO.selectOne(resultSet.getInt(5)));
			request.setJustification(resultSet.getString(6));
			
			list.add(request);
		}
		
		return list;
	}

}
