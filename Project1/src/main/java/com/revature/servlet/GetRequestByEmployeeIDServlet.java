package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.RequestDAO;
import com.revature.model.ID;
import com.revature.model.Request;

/**
 * Servlet implementation class GetRequestByEmployeeIDServlet
 */
public class GetRequestByEmployeeIDServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRequestByEmployeeIDServlet() 
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
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Request> newRequest = null;
		
		ID id = mapper.readValue(request.getInputStream(), ID.class);
		
		try 
		{
			newRequest = requestDAO.selectByEmployeeId(id.getId());
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			newRequest = new ArrayList<Request>();
		}
		
		PrintWriter out = response.getWriter();
		String requestJSON = mapper.writeValueAsString(newRequest);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(requestJSON);
		out.flush();
	}

}
