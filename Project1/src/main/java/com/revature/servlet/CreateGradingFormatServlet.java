package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.GradingFormatDAO;
import com.revature.model.GradingFormat;

/**
 * Servlet implementation class CreateGradingFormatServlet
 */
public class CreateGradingFormatServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGradingFormatServlet() 
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
		GradingFormatDAO gDAO = new GradingFormatDAO();
		ObjectMapper mapper = new ObjectMapper();
		GradingFormat format = null;
		
		format = mapper.readValue(request.getInputStream(), GradingFormat.class);
		
		try 
		{
			gDAO.createNew(format);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		String message = "Request created!";
		
		PrintWriter out = response.getWriter();
		String messageJSON = mapper.writeValueAsString(message);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(messageJSON);
		out.flush();
	}

}
