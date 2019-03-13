package com.revature.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.EventDAO;
import com.revature.database.RequestDAO;
import com.revature.model.Event;

/**
 * Servlet implementation class CompleteRequestServlet
 */
public class CompleteRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EventDAO eventDAO = new EventDAO();
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		
		Event event = mapper.readValue(request.getInputStream(), Event.class);
		
		try 
		{
			eventDAO.sendUpdate(event);
			//requestDAO.sendUpdate(object);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
