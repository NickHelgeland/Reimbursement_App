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
import com.revature.database.MessageDAO;
import com.revature.model.Message;

/**
 * Servlet implementation class GetMessageServlet
 */
public class GetMessageServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessageServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Message> list = null;
		MessageDAO messageDAO = new MessageDAO();
		HttpSession session = request.getSession();
		
		try 
		{
			list = messageDAO.selectByEmployeeId((int)session.getAttribute("employeeID"));
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			list = new ArrayList<Message>();
		}
		
		PrintWriter out = response.getWriter();
		String messageJSON = mapper.writeValueAsString(list);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(messageJSON);
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
