package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.EventDAO;
import com.revature.database.RequestDAO;
import com.revature.model.Event;
import com.revature.model.ID;

/**
 * Servlet implementation class GetEventServlet
 */
public class GetEventServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEventServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EventDAO eventDAO = new EventDAO();
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		Event event = null;
		ID id = mapper.readValue(request.getInputStream(), ID.class);
		
		try 
		{
			event = eventDAO.selectOne(requestDAO.selectOne(id.getId()).getEvent().getEventId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String eventJSON = mapper.writeValueAsString(event);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(eventJSON);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
