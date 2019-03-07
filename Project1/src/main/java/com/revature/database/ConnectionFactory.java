package com.revature.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory 
{
	private static ConnectionFactory connectionFactory = new ConnectionFactory();

	private static Connection connection;
	
	private ConnectionFactory()
	{
		super();
		this.initializeConnection();
	}
	
	private void initializeConnection()
	{	
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			connection = DriverManager.getConnection("jdbc:oracle:thin:@c9mtdb.ct5sfjoxigni.us-east-2.rds.amazonaws.com:1521:ORCL", 
					"cloud9", "memeteam");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}  
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionFactory getInstance()
	{
		if(connectionFactory == null)
		{
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
	public Connection getConnection()
	{	
		return connection;
	}
}
