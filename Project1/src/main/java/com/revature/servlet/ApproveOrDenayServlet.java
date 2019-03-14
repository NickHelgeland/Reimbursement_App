package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.RequestDAO;
import com.revature.model.ApproveOrDeny;
import com.revature.model.Request;

/**
 * Servlet implementation class ApproveOrDenayServlet
 */
public class ApproveOrDenayServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveOrDenayServlet() 
    {
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
		RequestDAO requestDAO = new RequestDAO();
		ObjectMapper mapper = new ObjectMapper();
		HttpSession session = request.getSession();
		Request newRequest = null;
		String message = "";
		
		ApproveOrDeny approveOrDeny = mapper.readValue(request.getInputStream(), ApproveOrDeny.class);
		
		try 
		{
			newRequest = requestDAO.selectOne(approveOrDeny.getId());
			if(approveOrDeny.isApproved())
			{
				if(session.getAttribute("type").toString().equals("supervisor"))
				{
					newRequest.setStatus("pending head approval");
				}
				else if(session.getAttribute("type").toString().equals("head"))
				{
					newRequest.setStatus("pending final approval");	
				}
				else if(session.getAttribute("type").toString().equals("bc"))
				{
					if(newRequest.getStatus().equals("pending confirmation"))
					{
						newRequest.setStatus("archived");
					}
					else
					{
						newRequest.setStatus("pending completion");
					}
				}
				message = "approved!";
			}
			else
			{
				newRequest.setStatus("denied");
				
				message = "denied!";
			}
			requestDAO.sendUpdate(newRequest);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		String messageJSON = mapper.writeValueAsString(message);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(messageJSON);
		out.flush();
	}

}
