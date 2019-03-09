package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.Login;
import com.revature.model.LoginInfo;

public class LoginServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		LoginInfo info = null;
		ObjectMapper mapper = new ObjectMapper();
		info = mapper.readValue(request.getInputStream(), LoginInfo.class);
		Login login = new Login();
		
		if(login.verify(info))
		{
			System.out.println("forward");
			request.getRequestDispatcher("/home.html").forward(request, response);
		}
		else
		{
			PrintWriter writer = response.getWriter();
			writer.write("Invalid Login");
		}
	}
	
}
