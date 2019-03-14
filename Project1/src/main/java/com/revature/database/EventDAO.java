package com.revature.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.model.Event;

public class EventDAO implements Insert<Event>, Update<Event>, Select<Event>
{
	ConnectionFactory factory = ConnectionFactory.getInstance();
	
	GradingFormatDAO gradingDAO = new GradingFormatDAO();

	@Override
	public Event selectOne(int id) throws SQLException 
	{
		Event event = new Event();
		
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM EVENTS WHERE EVENTID=" 
				+ "'" + id + "'" );
		
		while(resultSet.next())
		{
			event.setEventId(resultSet.getInt(1));
			event.setEventType(resultSet.getString(2));
			event.setGradingFormat(gradingDAO.selectOne(resultSet.getInt(3)));
			event.setEventDate(resultSet.getString(4));
			event.setEventTime(resultSet.getString(5));
			event.setEventLocation(resultSet.getString(6));
			event.setEventDescription(resultSet.getString(7));
			event.setEventStatus(resultSet.getString(8));
		}
		
		return event;
	}

	@Override
	public ArrayList<Event> selectAll() throws SQLException 
	{
		ArrayList<Event> eventList = new ArrayList<Event>();
		
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM EVENTS");
		
		while(resultSet.next())
		{
			Event event = new Event();
			
			event.setEventId(resultSet.getInt(1));
			event.setEventType(resultSet.getString(2));
			event.setGradingFormat(gradingDAO.selectOne(resultSet.getInt(3)));
			event.setEventDate(resultSet.getString(4));
			event.setEventTime(resultSet.getString(5));
			event.setEventLocation(resultSet.getString(6));
			event.setEventDescription(resultSet.getString(7));
			event.setEventStatus(resultSet.getString(8));
			
			eventList.add(event);
		}
		
		return eventList;
	}

	@Override
	public void sendUpdate(Event object) throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT * FROM EVENTS WHERE EVENTID=" 
				+ "'" + object.getEventId() + "'");
		
		while(resultSet.next())
		{
			Statement update = connection.createStatement();
			
			if(!object.getEventType().equals(resultSet.getString(2)))
			{
				update.executeQuery("UPDATE EVENTS SET EVENT_TYPE=" + "'" + object.getEventType() 
						+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
			if(object.getGradingFormat().getGradingFormatId() != resultSet.getInt(3))
			{
				update.executeQuery("UPDATE EVENTS SET GRADING_FORMAT_ID=" + "'" 
						+ object.getGradingFormat().getGradingFormatId()
						+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
			if(!object.getEventDate().equals(resultSet.getString(4)))
			{
				update.executeQuery("UPDATE EVENTS SET EVENT_DATE=" + "'" + object.getEventDate() 
				+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
			if(!object.getEventTime().equals(resultSet.getString(5)))
			{
				update.executeQuery("UPDATE EVENTS SET EVENT_TIME=" + "'" + object.getEventTime() 
				+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
			if(!object.getEventLocation().equals(resultSet.getString(6)))
			{
				update.executeQuery("UPDATE EVENTS SET EVENT_LOCATION=" + "'" + object.getEventLocation() 
				+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
			if(!object.getEventDescription().equals(resultSet.getString(7)))
			{
				update.executeQuery("UPDATE EVENTS SET EVENT_DESCRIPTION=" + "'" + object.getEventDescription() 
				+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
			if(!object.getEventStatus().equals(resultSet.getString(8)))
			{
				update.executeQuery("UPDATE EVENTS SET EVENT_STATUS=" + "'" + object.getEventStatus() 
				+ "' WHERE EVENTID=" + "'" + object.getEventId() + "'");
			}
		}
	}

	@Override
	public void createNew(Event object) throws SQLException 
	{
		Connection connection = factory.getConnection();
		
		PreparedStatement statement = connection.prepareStatement("INSERT INTO EVENTS "
				+ "VALUES(?,?,?,TO_DATE(?,'yyyy-mm-dd'),TO_DATE(?,'yyyy-mm-dd'),?,?,?)");
		statement.setInt(1, object.getEventId());
		statement.setString(2, object.getEventType());
		statement.setInt(3, object.getGradingFormat().getGradingFormatId());
		statement.setString(4, object.getEventDate());
		statement.setString(5, object.getEventTime());
		statement.setString(6, object.getEventLocation());
		statement.setString(7, object.getEventDescription());
		statement.setString(8, object.getEventStatus());
		
		statement.executeQuery();
	}
	
	public int getNewID() throws SQLException
	{
		Connection connection = factory.getConnection();
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery("SELECT EVENTID_SEQ.NEXTVAL FROM DUAL");
		
		int id = 0;
		
		while(resultSet.next())
		{
			id = resultSet.getInt(1);
		}
		
		return id;
	}

}
