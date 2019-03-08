package com.revature.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.model.LoginInfo;

public class Login 
{
	ConnectionFactory factory = ConnectionFactory.getInstance();
	
	public boolean verify(LoginInfo info)
	{
		Connection connection = factory.getConnection();
		
		boolean verified = false;
		
		try 
		{
			PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM LOGIN WHERE USERNAME=? AND PASSWORD=?");
			statement.setString(1, info.getUsername());
			statement.setString(2, info.getPassword());
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			if(resultSet.getInt(1) == 1)
			{
				verified = true;
			}			
		} 
		catch (SQLException e) 
		{
			
		}
		
		return verified;
	}
}
