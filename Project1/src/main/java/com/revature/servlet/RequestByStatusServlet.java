package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.RequestDAO;
import com.revature.model.Request;

/**
 * Servlet implementation class RequestByStatusServlet
 */
public class RequestByStatusServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestByStatusServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();
		
		String status = "";
		ArrayList<Request> requestList = null;
		
		if(session.getAttribute("type").toString().equals("superior"))
		{
			status = "pending superior approval";
		}
		else if(session.getAttribute("type").toString().equals("head"))
		{
			status = "pending head approval";
		}
		else if(session.getAttribute("type").toString().equals("bc"))
		{
			status = "pending final approval";
		}
		
		
		try 
		{
			requestList = requestDAO.selectPendingApproval(status, 
					(int)session.getAttribute("employeeID"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			requestList = new ArrayList<Request>();
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
		doGet(request,response);
	}
}
