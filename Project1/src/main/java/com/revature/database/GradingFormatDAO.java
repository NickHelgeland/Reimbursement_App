package com.revature.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.model.GradingFormat;

public class GradingFormatDAO implements Insert<GradingFormat>, Select<GradingFormat>
{
	ConnectionFactory factory = ConnectionFactory.getInstance();

	@Override
	public GradingFormat selectOne(int id) throws SQLException 
	{
		GradingFormat format = new GradingFormat();
		
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM GRADING_FORMAT WHERE GRADING_FORMAT_ID=" 
				+ "'" + id + "'");
		
		while(resultSet.next())
		{
			format.setGradingFormatId(resultSet.getInt(1));
			format.setGradingScale(resultSet.getString(2));
			format.setPassCondition(resultSet.getString(3));
		}
		
		return format;
	}

	@Override
	public ArrayList<GradingFormat> selectAll() throws SQLException 
	{
		ArrayList<GradingFormat> formatList = new ArrayList<GradingFormat>();
		
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM GRADING_FORMAT");
		
		while(resultSet.next())
		{
			GradingFormat format = new GradingFormat();
			format.setGradingFormatId(resultSet.getInt(1));
			format.setGradingScale(resultSet.getString(2));
			format.setPassCondition(resultSet.getString(3));
			
			formatList.add(format);
		}
		
		return formatList;
	}

	@Override
	public void createNew(GradingFormat object) throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO GRADING_FORMAT " 
				+ "VALUES(GRADING_FORMAT_SEQ.NEXTVAL,?,?)");
		statement.setString(1, object.getGradingScale());
		statement.setString(2, object.getPassCondition());
		
		statement.executeQuery();
	}
	
	public GradingFormat selectByScale(String scale) throws SQLException
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM GRADING_FORMAT WHERE "
				+ "GRADING_SCALE=" + "'" + scale + "'");
		
		GradingFormat gradingScale = new GradingFormat();
		
		while(resultSet.next())
		{
			gradingScale.setGradingFormatId(resultSet.getInt(1));
			gradingScale.setGradingScale(resultSet.getString(2));
			gradingScale.setPassCondition(resultSet.getString(3));
		}
		
		return gradingScale;
	}

}
