package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.database.EmployeeDAO;
import com.revature.model.Employee;

/**
 * Servlet implementation class GetEmployee
 */
public class GetEmployee extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmployee() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * localhost:8080/Project1/api/get-employee
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		EmployeeDAO dao = new EmployeeDAO();
		ObjectMapper mapper = new ObjectMapper();
		Employee employee = new Employee();

		employee.setEmployeeId(1);
		Employee fullEmployee = null;
		
		try 
		{
			fullEmployee = dao.selectOne(employee);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
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
