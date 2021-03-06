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
import com.revature.database.EmployeeDAO;
import com.revature.model.Employee;

/**
 * Servlet implementation class GetEmployeeList
 */
public class GetEmployeeList extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployeeList() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * /Project1/api/get-employee-list
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		EmployeeDAO dao = new EmployeeDAO();		
		ArrayList<Employee> fullEmployee = null;
		
		try 
		{
			fullEmployee = dao.selectAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		
		String employeeJSON = mapper.writeValueAsString(fullEmployee);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(employeeJSON);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{
//
//	}

}
