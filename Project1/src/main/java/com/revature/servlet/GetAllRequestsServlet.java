package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.RequestDAO;
import com.revature.model.Request;

/**
 * Servlet implementation class GetAllRequestsServlet
 */
public class GetAllRequestsServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllRequestsServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Request> requestList = null;
		
		try
		{
			requestList = requestDAO.selectAll();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String requestJSON = mapper.writeValueAsString(requestList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(requestJSON);
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
