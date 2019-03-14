package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.EventDAO;
import com.revature.database.RequestDAO;
import com.revature.model.Event;
import com.revature.model.ID;
import com.revature.model.Request;

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
		Request newRequest = null;
		Event event = null;
		
		ID id = mapper.readValue(request.getInputStream(), ID.class);
		
		try 
		{
			newRequest = requestDAO.selectOne(id.getId());
			event = newRequest.getEvent();
			eventDAO.sendUpdate(event);
			newRequest.setStatus("pending confirmation");
			requestDAO.sendUpdate(newRequest);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String message = "Status updated!";
		
		PrintWriter out = response.getWriter();
		String messageJSON = mapper.writeValueAsString(message);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(messageJSON);
		out.flush();
	}

}
