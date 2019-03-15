package com.revature.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.model.Message;

public class MessageDAO implements Insert<Message>, Select<Message>, Delete<Message>
{
	ConnectionFactory factory = ConnectionFactory.getInstance();
	
	@Override
	public void performDelete(Message object) throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		statement.executeQuery("DELETE FROM MESSAGES WHERE EMPLOYEEID=" + "'" 
				+ object.getTargetEmployeeId() + "'");
	}

	@Override
	public void createNew(Message object) throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO MESSAGES "
				+ "VALUES(MESSAGE_SEQ.NEXTVAL,?,?,?)");
		statement.setInt(1, object.getTargetEmployeeId());
		statement.setString(2, object.getMessage());
		statement.setInt(3, object.getSender());
	}

	@Override
	public Message selectOne(int id) throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGES WHERE MESSAGEID=" 
				+ "'" + id + "'");
		
		Message message = new Message();
		
		while(resultSet.next())
		{
			message.setMessageId(resultSet.getInt(1));
			message.setTargetEmployeeId(resultSet.getInt(2));
			message.setMessage(resultSet.getString(3));
			message.setSender(resultSet.getInt(4));
		}
		
		return message;
	}

	@Override
	public ArrayList<Message> selectAll() throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGES");
		
		ArrayList<Message> list = new ArrayList<Message>();
		
		while(resultSet.next())
		{
			Message message = new Message();
			
			message.setMessageId(resultSet.getInt(1));
			message.setTargetEmployeeId(resultSet.getInt(2));
			message.setMessage(resultSet.getString(3));
			message.setSender(resultSet.getInt(4));
			
			list.add(message);
		}
		
		return list;
	}
	
	public ArrayList<Message> selectByEmployeeId(int id) throws SQLException
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM MESSAGES WHERE EMPLOYEEID=" 
				+ "'" + id + "'");
		
		ArrayList<Message> list = new ArrayList<Message>();
		
		while(resultSet.next())
		{
			Message message = new Message();
			
			message.setMessageId(resultSet.getInt(1));
			message.setTargetEmployeeId(resultSet.getInt(2));
			message.setMessage(resultSet.getString(3));
			message.setSender(resultSet.getInt(4));
			
			list.add(message);
		}
		
		return list;
	}

}
