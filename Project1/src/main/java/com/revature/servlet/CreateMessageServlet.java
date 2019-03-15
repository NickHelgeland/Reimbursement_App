package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.MessageDAO;
import com.revature.model.Message;

/**
 * Servlet implementation class CreateMessageServlet
 */
public class CreateMessageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMessageServlet() 
    {
        super();
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
		ObjectMapper mapper = new ObjectMapper();
		MessageDAO messageDAO = new MessageDAO();
		String status = "Failure!";
		Message message = mapper.readValue(request.getInputStream(), Message.class);
		
		try 
		{
			messageDAO.createNew(message);
			status = "Success!";
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String statusJSON = mapper.writeValueAsString(status);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(statusJSON);
		out.flush();
	}

}
